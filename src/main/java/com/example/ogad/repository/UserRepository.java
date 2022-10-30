package com.example.ogad.repository;

import com.example.ogad.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(long userId);
    User findByUserName(String userName);
}
