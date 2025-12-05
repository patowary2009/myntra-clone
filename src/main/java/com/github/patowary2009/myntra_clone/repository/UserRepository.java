package com.github.patowary2009.myntra_clone.repository;

import com.github.patowary2009.myntra_clone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository<Type, ID Type>
public interface UserRepository extends JpaRepository<User, Long>  {
    // Magic Method: Spring automatically generates the SQL for this!
    // SQL equivalent: SELECT * FROM users WHERE userName = ?
    Optional<User> findByUserName(String userName);
}
