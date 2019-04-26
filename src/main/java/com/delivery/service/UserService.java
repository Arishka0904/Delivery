package com.delivery.service;

import com.delivery.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public void addNewUser(User user);

    public boolean isUserExist(User user);

    public boolean isEmailExist(User user);

    public boolean activateUser(String code);

    public List<User> findAll();

    public void updateUserRole(User user, String username, Map<String, String> form);

    public void updateProfile(User user, String password, String email);

}
