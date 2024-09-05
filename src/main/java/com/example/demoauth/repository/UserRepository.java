package com.example.demoauth.repository;

import com.example.demoauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email = :emailOrUsername " +
            "OR u.username = :emailOrUsername")
    Optional<User> findByUsernameOrEmail(String emailOrUsername);
}
