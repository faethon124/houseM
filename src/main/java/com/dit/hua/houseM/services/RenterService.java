package com.dit.hua.houseM.services;

import com.dit.hua.houseM.entities.Renter;
import com.dit.hua.houseM.repositories.RenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenterService {
    private final RenterRepository renterRepository;

    @Autowired
    public RenterService(RenterRepository renterRepository) {
        this.renterRepository = renterRepository;
    }

    // Fetch all renters
    public List<Renter> findAll() {
        return renterRepository.findAll();
    }

    // Find renter by ID
    public Renter findById(Long id) {
        return renterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Renter not found with ID: " + id));
    }

    // Find renters by username
    public Renter findByUsername(String username) {
        return renterRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Renter not found with username: " + username));
    }

    // Find renters by email
    public List<Renter> findByEmail(String email) {
        return renterRepository.findByEmail(email);
    }

    // Find renters by tax number (TPN)
    public List<Renter> findByTpn(String tpn) {
        return renterRepository.findByTpn(tpn);
    }

    // Save a new or updated renter
    public Renter save(Renter renter) {
        return renterRepository.save(renter);
    }

    // Update an existing renter
    public Renter update(Long id, Renter updatedRenter) {
        Renter existingRenter = findById(id);
        existingRenter.setFirstName(updatedRenter.getFirstName());
        existingRenter.setLastName(updatedRenter.getLastName());
        existingRenter.setUsername(updatedRenter.getUsername());
        existingRenter.setPassword(updatedRenter.getPassword());
        existingRenter.setEmail(updatedRenter.getEmail());
        existingRenter.setPhone(updatedRenter.getPhone());
        existingRenter.setTpn(updatedRenter.getTpn());
        return renterRepository.save(existingRenter);
    }

    // Delete renter by ID
    public void delete(Long id) {
        renterRepository.deleteById(id);
    }
}
