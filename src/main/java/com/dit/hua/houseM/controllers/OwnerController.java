package com.dit.hua.houseM.controllers;


import com.dit.hua.houseM.entities.Owner;
import com.dit.hua.houseM.services.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    // Endpoint to get all owners
    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> owners = ownerService.findAll();
        return ResponseEntity.ok(owners);
    }

    // Endpoint to get a single owner by ID
    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id) {
        Owner owner = ownerService.findById(id);
        return ResponseEntity.ok(owner);
    }

    // Endpoint to create a new owner
    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
        // Validate required fields
        if (owner.getFirstName() == null || owner.getLastName() == null || owner.getUsername() == null ||
                owner.getPassword() == null || owner.getTPN() == null || owner.getEmail() == null || owner.getPhone() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Owner savedOwner = ownerService.save(owner);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOwner);
    }

    // Endpoint to update an existing owner
    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody Owner updatedOwner) {
        // Check if the owner exists
        Owner existingOwner = ownerService.findById(id);
        if (existingOwner == null) {
            return ResponseEntity.notFound().build();
        }

        // Update fields
        if (updatedOwner.getFirstName() != null) {
            existingOwner.setFirstName(updatedOwner.getFirstName());
        }
        if (updatedOwner.getLastName() != null) {
            existingOwner.setLastName(updatedOwner.getLastName());
        }
        if (updatedOwner.getUsername() != null) {
            existingOwner.setUsername(updatedOwner.getUsername());
        }
        if (updatedOwner.getPassword() != null) {
            existingOwner.setPassword(updatedOwner.getPassword());
        }
        if (updatedOwner.getTPN() != null) {
            existingOwner.setTPN(updatedOwner.getTPN());
        }
        if (updatedOwner.getEmail() != null) {
            existingOwner.setEmail(updatedOwner.getEmail());
        }
        if (updatedOwner.getPhone() != null) {
            existingOwner.setPhone(updatedOwner.getPhone());
        }

        // Save the updated owner
        Owner savedOwner = ownerService.save(existingOwner);

        return ResponseEntity.ok(savedOwner);
    }


    // Endpoint to delete an owner by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        ownerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}



