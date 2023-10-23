package com.example.user.service;

import com.example.user.entity.UserInfo;

public interface UserService {

    UserInfo getUser(Long id);
    UserInfo saveUser(UserInfo userInfo);
    UserInfo changUserEmail(Long id, String new_email);
    void deleteUser(Long id);
}
