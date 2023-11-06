package com.example.user.repositroy;

import com.example.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositroy extends JpaRepository<User, Long> {

}

