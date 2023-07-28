package org.pahappa.systems.kimanyisacco.DAO;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.pahappa.systems.kimanyisacco.Config.SessionConfiguration;
import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.Transact;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.models.adminLogin;

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

    // get all the mebers
    public List<User> getUsers() {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("FROM User WHERE status = :status");
            query.setParameter("status", 0);
            List<User> selection = query.list();
            return selection;
        } catch (Exception e) {
            System.out.println("No user availabe");
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    // get the admin
    public adminLogin getAdmin(String adminID) {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Query query = session.createQuery("FROM adminLogin WHERE admin_id = :adminID");
        query.setParameter("adminID", adminID);
        return (adminLogin) query.uniqueResult();

    }

    // login method
    public User getMember(String userid) {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        Query query = session.createQuery("FROM User WHERE member_id = :userID");
        query.setParameter("userID", userid);
        return (User) query.uniqueResult();

    }

    public boolean deleteMember(String memberId) {
        Transaction transaction = null;

        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Create a criteria instance for the User class
            Criteria criteria = session.createCriteria(User.class);
            // create criteria to check the account for the member
            criteria.add(Restrictions.eq("memberId", memberId));
            // Get the user (if found) based on the criteria
            User user = (User) criteria.uniqueResult();
            if ((user != null) && (user.getAccount().getAmount() == 0)) {
                // Delete the user
                user.setStatus(1);
                session.update(user);
                transaction.commit();
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean getAccountByUSerId(String memberId, Double amount) {
        Transaction transaction = null;
        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Get the current timestamp
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

            // Retrieve all accounts

            List<Account> accounts = session.createCriteria(Account.class).list();

            // Find the account with the provided memberId
            Account accToUpdate = null;
            for (Account account : accounts) {
                if (account.getUser().getMemberId().equals(memberId)) {
                    accToUpdate = account;
                    break;
                }
            }

            if (accToUpdate != null) {
                System.out.println("Current Amount: " + accToUpdate.getAmount());
                System.out.println("Amount to Add: " + amount);
                Double balance = accToUpdate.getAmount() + amount;
                System.out.println("New Balance: " + balance);
                accToUpdate.setAmount(balance);
                // set the transaction
                Transact transact = new Transact();
                transact.setAmount(amount);
                transact.setMemberid(memberId);
                transact.setType("Deposit");
                transact.setTransactionDate(currentTimestamp);
                System.out.println("Transaction Date: " + transact.getTransactionDate());
                session.save(transact);
                session.update(accToUpdate);
                transaction.commit(); // Commit the transaction
                System.out.println("Updated Amount: " + accToUpdate.getAmount());

                return true;
            } else {
                System.out.println("Account not found for MemberID: " + memberId);
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback the transaction in case of an exception
            }
            e.printStackTrace();
            return false;
        }
    }

    // perform withdrawals
    public boolean getWithdrawals(String memberId, Double amount) {
        Transaction transaction = null;
        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Get the current timestamp
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

            // Retrieve all accounts

            List<Account> accounts = session.createCriteria(Account.class).list();

            // Find the account with the provided memberId
            Account accToUpdate = null;
            for (Account account : accounts) {
                if (account.getUser().getMemberId().equals(memberId)) {
                    accToUpdate = account;
                    break;
                }
            }

            if (accToUpdate != null) {
                System.out.println("Current Amount: " + accToUpdate.getAmount());
                System.out.println("Amount to Deduct: " + amount);
                if (amount < accToUpdate.getAmount() && amount > 1000) {
                    Double balance = accToUpdate.getAmount() - amount;
                    System.out.println("New Balance: " + balance);
                    accToUpdate.setAmount(balance);
                    // set trasanction fields
                    Transact transact = new Transact();
                    transact.setAmount(amount);
                    transact.setMemberid(memberId);
                    transact.setTransactionDate(currentTimestamp);
                    transact.setType("Withdraw");
                    session.update(accToUpdate);
                    session.save(transact);
                    transaction.commit(); // Commit the transaction
                    System.out.println("Updated Amount: " + accToUpdate.getAmount());
                    return true;
                } else {
                    System.out.println("Insuffient amount");
                    return false;
                }

            } else {
                System.out.println("Account not found for MemberID: " + memberId);
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback the transaction in case of an exception
            }
            e.printStackTrace();
            return false;
        }
    }

    // Get all transactions
    public List<Transact> getTransactions(String memberid) {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("FROM Transact WHERE member_id = :memberid");
            query.setParameter("memberid", memberid);
            List<Transact> selection = query.list();
            return selection;
        } catch (Exception e) {
            System.out.println("No transactions availabe");
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    public List<Transact> getAllTransactions() {
        Session session = SessionConfiguration.getSessionFactory().openSession();
        try {

            return session.createCriteria(Transact.class).list();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public boolean updateUser(User user) {
        Transaction transaction = null;

        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
