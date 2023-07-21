package org.pahappa.systems.kimanyisacco.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.pahappa.systems.kimanyisacco.Config.SessionConfiguration;
import org.pahappa.systems.kimanyisacco.models.User;

public class UserDAO {
    public void save(User user) {
        Transaction transaction = null;

        try {
            System.out.println(user.getFirstname());
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            return session.createCriteria(User.class).list();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String deleteMember(String memberId) {
        Transaction transaction = null;

        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Create a criteria instance for the User class
            Criteria criteria = session.createCriteria(User.class);

            // Add a restriction to find the user with the given member ID
            criteria.add(Restrictions.eq("memberId", memberId));

            // Get the user (if found) based on the criteria
            User user = (User) criteria.uniqueResult();

            if (user != null) {
                // Delete the user
                session.delete(user);
                transaction.commit();
                return ("User with member ID " + memberId + " deleted successfully.");
            } else {
                return ("User with member ID " + memberId + " not found in the database.");
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "Error occured";
        }
    }
}
