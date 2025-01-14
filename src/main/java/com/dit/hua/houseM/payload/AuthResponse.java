package com.dit.hua.houseM.payload;

public class AuthResponse {
    private String role;
    private Long userId;
    private String message;

    public AuthResponse(String role, Long userId, String message) {
        this.role = role;
        this.userId = userId;
        this.message = message;
    }

    // Getters and Setters

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
