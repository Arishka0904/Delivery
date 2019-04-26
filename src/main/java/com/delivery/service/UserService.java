package com.delivery.service;

import com.delivery.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    void addNewUser(User user);

    User findByEmail(String email);

    User findByName(String name);

    boolean isUserExist(User user);

    boolean isEmailExist(User user);

    boolean activateUser(String code);

    List<User> findAll();

    void updateUserRole(User user, String username, Map<String, String> form);

    void updateProfile(User user, String password, String email);

}
