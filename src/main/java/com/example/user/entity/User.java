package com.example.user.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

package com.example.user.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String password;
}

