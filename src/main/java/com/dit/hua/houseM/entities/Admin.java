package com.dit.hua.houseM.entities;

import com.dit.hua.houseM.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name="Admin")
public class Admin extends BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_ADMIN;

    public Admin() {
        super();
        this.setRole(Role.ROLE_ADMIN);
    }

    public Admin(String username, String email, String password) {
        super(username, email, password);
        this.setRole(Role.ROLE_ADMIN);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Admin [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", role=" + role +"]";
    }
}