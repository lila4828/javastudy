package com.example.project7.repositroy;

import com.example.project7.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositroy extends JpaRepository<User, Long> {

}
