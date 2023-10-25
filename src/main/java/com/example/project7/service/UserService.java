package com.example.project7.service;

import com.example.project7.entity.User;

public interface UserService {
    User getUser(Long id);
    User saveUser(User user);
    User changUserEmail(Long id, String new_email);
    void deleteUser(Long id);
}
