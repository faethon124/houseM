package com.dit.hua.houseM.controllers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class AuthController {

    private final RestTemplate restTemplate;

    public AuthController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Display the login page
    @GetMapping("/login")
    public String login() {
        return "login"; // Returns login view
    }

    // Display the signup page
    @GetMapping("/signup")
    public String signup() {
        return "signup"; // Returns signup view
    }

    // Handle signup form submission
    @PostMapping("/signup")
    public String registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String role) {
        String url;
        if ("OWNER".equalsIgnoreCase(role)) {
            url = "http://localhost:8080/owners/add";
        } else if ("RENTER".equalsIgnoreCase(role)) {
            url = "http://localhost:8080/renters/add";
        } else {
            return "redirect:/signup?error=invalid_role"; // If the role is invalid
        }

        // Create a request payload for registration
        var request = new RegistrationRequest(username, email, password);

        // Send POST request to the respective controller
        restTemplate.postForObject(url, request, Void.class);

        return "redirect:/login?success"; // Redirect to login after successful signup
    }

    // DTO class for sending registration data
    private static class RegistrationRequest {
        public String username;
        public String email;
        public String password;

        public RegistrationRequest(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }
    }
}



