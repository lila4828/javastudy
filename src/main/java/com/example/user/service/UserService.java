package com.example.user.service;

import com.example.user.entity.User;

public interface UserService {

    User getUser(Long id);
    User saveUser(User user);
    User changUserEmail(Long id, String new_email);
    void deleteUser(Long id);
}
