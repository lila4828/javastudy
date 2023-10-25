package com.example.project7.service.impl;

import com.example.project7.entity.User;
import com.example.project7.repositroy.UserRepositroy;
import com.example.project7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepositroy userRepositroy;
    @Autowired
    public UserServiceImpl(UserRepositroy userRepositroy){
        this.userRepositroy = userRepositroy;
    }

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
