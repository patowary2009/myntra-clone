package com.github.patowary2009.myntra_clone.controller;

import com.github.patowary2009.myntra_clone.dto.LoginRequest;
import com.github.patowary2009.myntra_clone.entity.User;
import com.github.patowary2009.myntra_clone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import  org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired // This injects the Repository so we can use it
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject the BCrypt encoder

    // --- REGISTER API (To create users with Hashed passwords) ---
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Check if username exists
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        // HASH THE PASSWORD before saving
        String plainPassword = user.getPassword();
        String hashedPassword = passwordEncoder.encode(plainPassword);
        user.setPassword(hashedPassword);

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")

    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {

        // 1. Check if user exists in DB
        Optional<User> userOptional = userRepository.findByUserName(loginRequest.getUsrName());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 2. Check if password matches

            // 3. USE MATCHES: (Plain Password from React, Hashed Password from DB)
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {

                Map<String, String> response = new HashMap<>();
                response.put("message", "Login Successful");
                response.put("token", "fake-jwt-token-12345"); // You will learn JWT later
                response.put("username", user.getUserName());
                return ResponseEntity.ok(response);
            }
        }
        // 3. If user not found OR password wrong
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or Password");
    }
}
