package com.example.user.service.impl;

import com.example.user.entity.UserInfo;
import com.example.user.repositroy.UserRepositroy;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositroy userRepositroy;

    @Override
    public UserInfo getUser(Long id) {
        Optional<UserInfo> user = userRepositroy.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public UserInfo saveUser(UserInfo userInfo) {
        UserInfo saveUser = userRepositroy.save(userInfo);
        System.out.println(saveUser);
        return saveUser;
    }

    @Override
    public UserInfo changUserEmail(Long id, String new_email) {
        Optional<UserInfo> user = userRepositroy.findById(id);
        UserInfo newUser;
        if(user.isPresent()) {
            newUser = user.get();
            newUser.setEmail(new_email);
        } else {
            throw new EntityNotFoundException();
        }
        return newUser;
    }

    @Override
    public void deleteUser(Long id) {
        Optional<UserInfo> selectUser = userRepositroy.findById(id);
        if(selectUser.isPresent()) {
            UserInfo user = selectUser.get();
            userRepositroy.delete(user);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
