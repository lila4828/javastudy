package com.example.java1120.domain;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER"),
    MANAGER("ROLE_MANAGER");

    Role(String value) {
        this.value = value;
    }
    private String value;

}
