package com.example.user.repositroy;

import com.example.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositroy extends JpaRepository<UserInfo, Long> {

}
