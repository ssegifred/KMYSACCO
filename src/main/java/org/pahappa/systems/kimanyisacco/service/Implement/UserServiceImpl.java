package org.pahappa.systems.kimanyisacco.service.Implement;

import java.util.List;

import org.pahappa.systems.kimanyisacco.DAO.UserDAO;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.service.Userservice;

public class UserServiceImpl implements Userservice {
    List<User> userList;

    private final UserDAO userDAO = new UserDAO();

    public void saveUser(User user) {

        // System.out.println(user.getFirstname());
        // userList = new ArrayList<>();
        // userList.add(user);
        // for (User users:userList){
        // System.out.println("User object Received: "+users.getLastname());}
        userDAO.save(user);
    }

    public List<User> getAllUsers() {

        return userDAO.getUsers();
    }

    public void deleteMember(String memberid) {
        System.out.println(userDAO.deleteMember(memberid));
    }
    // public List<adminLogin> admin(){
    // admin=userDAO.adminlog();
    // return admin;
    // }
}
