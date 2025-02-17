package com.dit.hua.houseM.services;

import com.dit.hua.houseM.CustomUserDetails;
import com.dit.hua.houseM.entities.Admin;
import com.dit.hua.houseM.entities.Owner;
import com.dit.hua.houseM.entities.Renter;
import com.dit.hua.houseM.repositories.AdminRepository;
import com.dit.hua.houseM.repositories.OwnerRepository;
import com.dit.hua.houseM.repositories.RenterRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final OwnerRepository ownerRepository;
    private final RenterRepository renterRepository;
    private final AdminRepository adminRepository;

    public CustomUserDetailService(OwnerRepository ownerRepository, RenterRepository renterRepository, AdminRepository adminRepository) {
        this.ownerRepository = ownerRepository;
        this.renterRepository = renterRepository;
        this.adminRepository = adminRepository;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Fetch the owner using the repository
//        Owner owner = ownerRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
//        // Create and return a UserDetails object
//        return new CustomUserDetails(owner.getUsername(), owner.getPassword(), "ROLE_OWNER");
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Fetch the owner using the repository
//        Renter renter = renterRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
//        // Create and return a UserDetails object
//        return new CustomUserDetails(renter.getUsername(), renter.getPassword(), "ROLE_RENTER");
//    }
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // Έλεγχος αν είναι Owner
    Optional<Owner> owner = ownerRepository.findByUsername(username);
    if (owner.isPresent()) {
        return new CustomUserDetails(owner.get().getUsername(), owner.get().getPassword(), "ROLE_OWNER");
    }

    // Έλεγχος αν είναι Renter
    Optional<Renter> renter = renterRepository.findByUsername(username);
    if (renter.isPresent()) {
        return new CustomUserDetails(renter.get().getUsername(), renter.get().getPassword(), "ROLE_RENTER");
    }
    Optional<Admin> admin = adminRepository.findByUsername(username);
    if (admin.isPresent()) {
        return new CustomUserDetails(admin.get().getUsername(), admin.get().getPassword(), "ROLE_ADMIN");
    }

    throw new UsernameNotFoundException("User not found with username: " + username);
}
    }