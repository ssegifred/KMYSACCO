package org.pahappa.systems.kimanyisacco.service;

import java.util.List;

import org.pahappa.systems.kimanyisacco.models.Transact;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.models.adminLogin;

public interface Userservice {

    void saveUser(User user);

    List<User> getAllUsers();

    boolean deleteMember(String memberid);

    adminLogin getAdmin(String id);

    User getMember(String id);

    boolean updateUser(User user);

    boolean getAccountByUserId(String id, Double amount);

    boolean getWithdrawals(String id, Double amount);

    List<Transact> getAllTransactions(String id);

    List<Transact> getTransactions();

}
