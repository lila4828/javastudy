package com.example.java1120.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name="member_login")
public class Member {
    @Id
    private String id;
    private String name;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
