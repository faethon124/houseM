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

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

    public List<Owner> findByFirstNameOrderByFirstNameAsc(String firstName) {
        return ownerRepository.findByFirstNameOrderByFirstNameAsc(firstName);
    }

    public List<Owner> findByLastNameOrderByLastNameAsc(String lastName) {
        return ownerRepository.findByLastNameOrderByLastNameAsc(lastName);
    }
//
//    public List<Owner> findByOrderByUsernameAsc(String username) {
//        return ownerRepository.findByLastNameOrderByUsernameAsc(username);
//    }
public List<Owner> findByUsernameOrderByUsernameAsc(String username) {
    return ownerRepository.findByUsernameOrderByUsernameAsc(username);
}
    public Owner findByUsername(String username) {
        return ownerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
    }
}

