package com.example.ogad.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode
@Entity
@Table(name = "user_role")
@Data
public class UserRole extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "user_role_id")
    private long userRoleId;

    @Basic
    @Column(name = "user_id")
    private long userId;

    @Basic
    @Column(name = "role_id")
    private long roleId;

    @OneToMany
    @JoinColumn(name = "role_id")
    List<Role> roles;

}
