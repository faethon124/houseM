package com.dit.hua.houseM.services;

import com.dit.hua.houseM.entities.Admin;
import com.dit.hua.houseM.entities.Owner;
import com.dit.hua.houseM.entities.Renter;
import com.dit.hua.houseM.repositories.AdminRepository;
import com.dit.hua.houseM.repositories.OwnerRepository;
import com.dit.hua.houseM.repositories.RenterRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final OwnerRepository ownerRepository;
    private final RenterRepository renterRepository;

    public CustomUserDetailService(AdminRepository adminRepository,
                                    OwnerRepository ownerRepository,
                                    RenterRepository renterRepository) {
        this.adminRepository = adminRepository;
        this.ownerRepository = ownerRepository;
        this.renterRepository = renterRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Check if user is an Admin
        Admin admin = adminRepository.findByUsername(username).orElse(null);
        if (admin != null) {
            return User.builder()
                    .username(admin.getUsername())
                    .password(admin.getPassword())
                    .roles("ADMIN")
                    .build();
        }

        // Check if user is an Owner
        Owner owner = ownerRepository.findByUsername(username).orElse(null);
        if (owner != null) {
            return User.builder()
                    .username(owner.getUsername())
                    .password(owner.getPassword())
                    .roles("OWNER")
                    .build();
        }

        // Check if user is a Renter
        Renter renter = renterRepository.findByUsername(username).orElse(null);
        if (renter != null) {
            return User.builder()
                    .username(renter.getUsername())
                    .password(renter.getPassword())
                    .roles("RENTER")
                    .build();
        }

        // If no user is found, throw an exception
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
