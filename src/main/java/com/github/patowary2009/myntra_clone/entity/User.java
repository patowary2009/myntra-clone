package com.github.patowary2009.myntra_clone.entity;

import jakarta.persistence.*;

@Entity // Tells Spring this class maps to a Database Table
@Table(name="users") // The table name
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment ID
    private  Long id;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    private String email;

    // Constructors
    public User() {} // JPA needs an empty constructor

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    // Getters and Setters (You can generate these in IntelliJ: Right Click -> Generate -> Getters and Setters)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
