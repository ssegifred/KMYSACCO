package org.pahappa.systems.kimanyisacco.service;

import java.util.List;

import org.pahappa.systems.kimanyisacco.models.User;

public interface Userservice {

    void saveUser(User user);

    List<User> getAllUsers();

    void deleteMember(String memberid);
}
