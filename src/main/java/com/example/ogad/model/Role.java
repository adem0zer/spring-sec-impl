package com.example.ogad.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode
@Entity
@Table(name = "role")
@Data
public class Role extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "role_id")
    private long roleId;

    @Basic
    @Column(name = "role_name")
    private String roleName;

}
