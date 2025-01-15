package com.dit.hua.houseM.controllers.rest;

import com.dit.hua.houseM.entities.Owner;
import com.dit.hua.houseM.entities.Renter;
import com.dit.hua.houseM.payload.AuthRequest;
import com.dit.hua.houseM.payload.AuthResponse;
import com.dit.hua.houseM.services.AdminService;
import com.dit.hua.houseM.services.OwnerService;
import com.dit.hua.houseM.services.RenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private RenterService renterService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        // Check if user exists as an admin
        if (adminService.isSingleAdmin(authRequest.getUsername(), authRequest.getPassword())) {
            return ResponseEntity.ok(new AuthResponse("Admin", 1L, "Login successful"));
        }

        // Check if user exists as an owner
        // Check if user exists as an owner
//        Owner owner = ownerService.findByUsername(authRequest.getUsername());
//        if (owner != null && passwordEncoder.matches(authRequest.getPassword(), owner.getPassword())) {
//            return ResponseEntity.ok(new AuthResponse("Owner", owner.getId(), "Login successful"));
//        }

        // Check if user exists as a renter
        Renter renter = renterService.findByUsername(authRequest.getUsername());
        if (renter != null && passwordEncoder.matches(authRequest.getPassword(), renter.getPassword())) {
            return ResponseEntity.ok(new AuthResponse("Renter", renter.getId(), "Login successful"));
        }


        // Invalid credentials
        // Invalid credentials
        return ResponseEntity.status(401).body("Invalid username or password");
    }
}


