package com.example.user.service.impl;

import com.example.user.entity.User;
import com.example.user.repositroy.UserRepositroy;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositroy userRepositroy;

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepositroy.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public User saveUser(User user) {
        User saveUser = userRepositroy.save(user);
        System.out.println(saveUser);
        return saveUser;
    }

    @Override
    public User changUserEmail(Long id, String new_email) {
        Optional<User> user = userRepositroy.findById(id);
        User newUser;
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
        Optional<User> selectUser = userRepositroy.findById(id);
        if(selectUser.isPresent()) {
            User user = selectUser.get();
            userRepositroy.delete(user);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
