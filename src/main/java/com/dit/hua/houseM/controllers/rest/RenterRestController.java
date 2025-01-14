package com.dit.hua.houseM.controllers.rest;

import com.dit.hua.houseM.entities.Renter;
import com.dit.hua.houseM.services.RenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/renters")
public class RenterRestController {

    private final RenterService renterService;

    @Autowired
    public RenterRestController(RenterService renterService) {
        this.renterService = renterService;
    }

    // Get all renters
    @GetMapping
    public List<Renter> getAllRenters() {
        return renterService.findAll();
    }

    // Get renter by ID
    @GetMapping("/{id}")
    public Renter getRenterById(@PathVariable Long id) {
        return renterService.findById(id);
    }

    // Find renters by username
    @GetMapping("/by-username")
    public ResponseEntity<Renter> getRenterByUsername(@RequestParam String username) {
        try {
            Renter renter = renterService.findByUsername(username);
            return ResponseEntity.ok(renter);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if not found
        }
    }

    // Find renters by email
    @GetMapping("/by-email")
    public List<Renter> getRentersByEmail(@RequestParam String email) {
        return renterService.findByEmail(email);
    }

    // Find renters by TPN
    @GetMapping("/by-tpn")
    public List<Renter> getRentersByTpn(@RequestParam String tpn) {
        return renterService.findByTpn(tpn);
    }

    // Add a new renter
    @PostMapping
    public Renter addRenter(@RequestBody Renter renter) {
        return renterService.save(renter);
    }

    // Update an existing renter
    @PutMapping("/{id}")
    public Renter updateRenter(@PathVariable Long id, @RequestBody Renter renter) {
        return renterService.update(id, renter);
    }

    // Delete a renter
    @DeleteMapping("/{id}")
    public String deleteRenter(@PathVariable Long id) {
        renterService.delete(id);
        return "Renter deleted successfully.";
    }
}
