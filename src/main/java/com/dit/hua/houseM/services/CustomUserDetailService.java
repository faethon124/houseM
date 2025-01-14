package com.dit.hua.houseM.services;

import com.dit.hua.houseM.CustomUserDetails;
import com.dit.hua.houseM.entities.Owner;
import com.dit.hua.houseM.entities.Renter;
import com.dit.hua.houseM.repositories.OwnerRepository;
import com.dit.hua.houseM.repositories.RenterRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final OwnerRepository ownerRepository;
    private final RenterRepository renterRepository;

    public CustomUserDetailService(OwnerRepository ownerRepository, RenterRepository renterRepository) {
        this.ownerRepository = ownerRepository;
        this.renterRepository = renterRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the owner using the repository
        Owner owner = ownerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        // Create and return a UserDetails object
        return new CustomUserDetails(owner.getUsername(), owner.getPassword(), "ROLE_OWNER");
    }
// TODO the same for owner

        Renter renter = renterRepository.findByUsername(username).orElse(null);
        if (renter != null) {
            return new CustomUserDetails(renter.getUsername(), renter.getPassword(), "ROLE_RENTER");
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
