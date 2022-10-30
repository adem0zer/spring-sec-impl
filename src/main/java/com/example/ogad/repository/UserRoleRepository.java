package com.example.ogad.repository;

import com.example.ogad.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByUserRoleId(long userRoleId);
}
