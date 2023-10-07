package com.spring.dao.repositories;

import com.spring.dao.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
   Optional<User> findByUserName(String username);
}
