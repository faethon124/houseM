package com.dit.hua.houseM.controllers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login"; // Returns login view
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password) {
        // Authenticate user and get role (pseudo-code)
        String role = authenticateUser(username, password);

        if ("OWNER".equals(role) ) {
            return "redirect:/owner/dashboard"; // Redirect to owner dashboard
        } else if ("ROLE_RENTER".equalsIgnoreCase(role)) {
            return "redirect:/renter/properties"; // Redirect to renter properties page
        } else {
            return "redirect:/login?error=invalid_credentials"; // Redirect back to login with error
        }
    }

    private String authenticateUser(String username, String password) {
        // Implement your authentication logic here
        // This is just a placeholder
        // In a real application, you would query the database or use Spring Security
        return "ROLE_RENTER"; // or "ROLE_OWNER"
    }
}



