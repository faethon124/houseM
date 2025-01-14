package com.dit.hua.houseM.controllers.rest;

import com.dit.hua.houseM.entities.Owner;
import com.dit.hua.houseM.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerRestController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerRestController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public List<Owner> getAllOwners() {
        return ownerService.findAll();  // Make sure this method returns the list of owners
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id) {
        Owner owner = ownerService.findById(id);
        if (owner != null) {
            return ResponseEntity.ok(owner);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/new")
    public Owner addOwner(@RequestBody Owner owner) {
        return ownerService.save(owner);
    }

    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
        if (owner.getFirstName() == null || owner.getLastName() == null || owner.getUsername() == null ||
                owner.getPassword() == null || owner.getTpn() == null || owner.getEmail() == null || owner.getPhone() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Owner savedOwner = ownerService.save(owner);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOwner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody Owner updatedOwner) {
        Owner existingOwner = ownerService.findById(id);
        if (existingOwner == null) {
            return ResponseEntity.notFound().build();
        }

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
        if (updatedOwner.getTpn() != null) {
            existingOwner.setTpn(updatedOwner.getTpn());
        }
        if (updatedOwner.getEmail() != null) {
            existingOwner.setEmail(updatedOwner.getEmail());
        }
        if (updatedOwner.getPhone() != null) {
            existingOwner.setPhone(updatedOwner.getPhone());
        }

        Owner savedOwner = ownerService.save(existingOwner);
        return ResponseEntity.ok(savedOwner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        ownerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // New Endpoint: Find owners by first name (ascending order)
    @GetMapping("/search/by-first-name")
    public List<Owner> getOwnersByFirstName(@RequestParam String firstName) {
        return ownerService.findByFirstNameOrderByFirstNameAsc(firstName);
    }

    // New Endpoint: Find owners by last name (ascending order)
    @GetMapping("/search/by-last-name")
    public List<Owner> getOwnersByLastName(@RequestParam String lastName) {
        return ownerService.findByLastNameOrderByLastNameAsc(lastName);
    }
}
