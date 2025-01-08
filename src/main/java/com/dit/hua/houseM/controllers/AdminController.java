package com.dit.hua.houseM.controllers;

import com.dit.hua.houseM.entities.Admin;
import com.dit.hua.houseM.entities.Owner;
import com.dit.hua.houseM.entities.Property;
import com.dit.hua.houseM.entities.Renter;
import com.dit.hua.houseM.services.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    private final AdminService adminService;
    private final PropertyService propertyService;
    private final OwnerService ownerService;
    private final RenterService renterService;

    public AdminRestController(AdminService adminService, PropertyService propertyService,
                               OwnerService ownerService, RenterService renterService) {
        this.adminService = adminService;
        this.propertyService = propertyService;
        this.ownerService = ownerService;
        this.renterService = renterService;
    }

    // Admin login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Admin admin) {
        Admin existingAdmin = adminService.findByUsername(admin.getUsername());
        if (existingAdmin != null && existingAdmin.getPassword().equals(admin.getPassword())) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    // Approve a property
    @PostMapping("/properties/{id}/approve")
    public ResponseEntity<String> approveProperty(@PathVariable Long id) {
        Property property = propertyService.findById(id);
        if (property == null) {
            return ResponseEntity.notFound().build();
        }
        property.setApproved(true);
        propertyService.save(property);
        return ResponseEntity.ok("Property approved successfully");
    }

    // Reject a property
    @PostMapping("/properties/{id}/reject")
    public ResponseEntity<String> rejectProperty(@PathVariable Long id) {
        Property property = propertyService.findById(id);
        if (property == null) {
            return ResponseEntity.notFound().build();
        }
        propertyService.deleteById(id);
        return ResponseEntity.ok("Property rejected and deleted");
    }

    // Get all unapproved properties
    @GetMapping("/properties/unapproved")
    public List<Property> getUnapprovedProperties() {
        return propertyService.findUnapproved();
    }

    // Approve an owner
    @PostMapping("/owners/{id}/approve")
    public ResponseEntity<String> approveOwner(@PathVariable Long id) {
        Owner owner = ownerService.findById(id);
        if (owner == null) {
            return ResponseEntity.notFound().build();
        }
        owner.setApproved(true);
        ownerService.save(owner);
        return ResponseEntity.ok("Owner approved successfully");
    }

    // Reject an owner
    @PostMapping("/owners/{id}/reject")
    public ResponseEntity<String> rejectOwner(@PathVariable Long id) {
        Owner owner = ownerService.findById(id);
        if (owner == null) {
            return ResponseEntity.notFound().build();
        }
        ownerService.deleteById(id);
        return ResponseEntity.ok("Owner rejected and deleted");
    }

    // Update an owner
    @PutMapping("/owners/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody Owner updatedOwner) {
        Owner existingOwner = ownerService.findById(id);
        if (existingOwner == null) {
            return ResponseEntity.notFound().build();
        }
        existingOwner.setFirstName(updatedOwner.getFirstName());
        existingOwner.setLastName(updatedOwner.getLastName());
        existingOwner.setEmail(updatedOwner.getEmail());
        existingOwner.setUsername(updatedOwner.getUsername());
        existingOwner.setPassword(updatedOwner.getPassword());
        existingOwner.setTPN(updatedOwner.getTPN());
        existingOwner.setPhone(updatedOwner.getPhone());
        Owner savedOwner = ownerService.save(existingOwner);
        return ResponseEntity.ok(savedOwner);
    }

    // Approve a renter
    @PostMapping("/renters/{id}/approve")
    public ResponseEntity<String> approveRenter(@PathVariable Long id) {
        Renter renter = renterService.findById(id);
        if (renter == null) {
            return ResponseEntity.notFound().build();
        }
        renter.setApproved(true);
        renterService.save(renter);
        return ResponseEntity.ok("Renter approved successfully");
    }

    // Reject a renter
    @PostMapping("/renters/{id}/reject")
    public ResponseEntity<String> rejectRenter(@PathVariable Long id) {
        Renter renter = renterService.findById(id);
        if (renter == null) {
            return ResponseEntity.notFound().build();
        }
        renterService.deleteById(id);
        return ResponseEntity.ok("Renter rejected and deleted");
    }

    // Update a renter
    @PutMapping("/renters/{id}")
    public ResponseEntity<Renter> updateRenter(@PathVariable Long id, @RequestBody Renter updatedRenter) {
        Renter existingRenter = renterService.findById(id);
        if (existingRenter == null) {
            return ResponseEntity.notFound().build();
        }
        existingRenter.setFirstName(updatedRenter.getFirstName());
        existingRenter.setLastName(updatedRenter.getLastName());
        existingRenter.setEmail(updatedRenter.getEmail());
        existingRenter.setUsername(updatedRenter.getUsername());
        existingRenter.setPassword(updatedRenter.getPassword());
        existingRenter.setPhone(updatedRenter.getPhone());
        Renter savedRenter = renterService.save(existingRenter);
        return ResponseEntity.ok(savedRenter);
    }

    // Get all approved users (both renters and owners)
    @GetMapping("/users/approved")
    public Map<String, List<?>> getApprovedUsers() {
        Map<String, List<?>> approvedUsers = new HashMap<>();
        approvedUsers.put("owners", ownerService.findApprovedOwners());
        approvedUsers.put("renters", renterService.findApprovedRenters());
        return approvedUsers;
    }
}

}
