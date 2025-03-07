package com.example.demoasm1java5.Repository;


import com.example.demoasm1java5.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
//    Optional<User> findByUsername(String username);
//    boolean existsByUsername(String username);
//    boolean existsByEmail(String email);
}
