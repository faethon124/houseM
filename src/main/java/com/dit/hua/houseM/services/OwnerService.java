package com.dit.hua.houseM.services;

import com.dit.hua.houseM.entities.Owner;
import com.dit.hua.houseM.repositories.OwnerRepository;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
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

    public Owner findByTaxNumber(String taxNumber) {
        return ownerRepository.findByTaxNumber(taxNumber)
                .orElseThrow(() -> new RuntimeException("Owner not found with tax number: " + taxNumber));
    }
}

