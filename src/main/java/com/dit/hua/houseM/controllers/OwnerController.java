package com.dit.hua.houseM.controllers;


import com.dit.hua.houseM.entities.Owner;
import com.dit.hua.houseM.services.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerRestController {

    private final OwnerService ownerService;

    public OwnerRestController(OwnerService ownerService) {
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
        Owner savedOwner = ownerService.save(owner);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOwner);
    }

    // Endpoint to update an existing owner
    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody Owner ownerDetails) {
        Owner existingOwner = ownerService.findById(id);

        // Update fields
        existingOwner.setName(ownerDetails.getName());
        existingOwner.setEmail(ownerDetails.getEmail());

        Owner updatedOwner = ownerService.save(existingOwner);
        return ResponseEntity.ok(updatedOwner);
    }

    // Endpoint to delete an owner by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        ownerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

public class OwnerController {

}
