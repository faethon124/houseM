package com.dit.hua.houseM.services;

import com.dit.hua.houseM.entities.Owner;
import com.dit.hua.houseM.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> findAll( ) {
        return ownerRepository.findAll(); // Ensure this fetches all owners from DB
    }

    // Find owner by ID
    public Owner findById(Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        return owner.orElse(null); // If owner exists, return it, otherwise return null
    }

    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

    // Other methods...

    public Owner findByUsername(String username) {
        return ownerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Owner not found with username: " + username));
    }

    public Owner findByEmail(String email) {
        return ownerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Owner not found with email: " + email));
    }

    public Owner findByTpn(String tpn) {
        return ownerRepository.findByTpn(tpn)
                .orElseThrow(() -> new RuntimeException("Owner not found with tax number: " + tpn));
    }
}

