package com.example.ogad.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode
@Entity
@Table(name = "auser")
@Data
public class User extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "user_id")
    private long userId;

    @Basic
    @Column(name = "user_name")
    private String userName;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "userId")
    List<UserRole> userRoles;
}
