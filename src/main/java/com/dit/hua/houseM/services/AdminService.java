package com.dit.hua.houseM.services;

import com.dit.hua.houseM.entities.Admin;
import com.dit.hua.houseM.entities.Owner;
import com.dit.hua.houseM.entities.Property;
import com.dit.hua.houseM.entities.Renter;
import com.dit.hua.houseM.repositories.AdminRepository;
import com.dit.hua.houseM.repositories.OwnerRepository;
import com.dit.hua.houseM.repositories.PropertyRepository;
import com.dit.hua.houseM.repositories.RenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;
    private final RenterRepository renterRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(AdminRepository adminRepository, PropertyRepository propertyRepository,
                        OwnerRepository ownerRepository, RenterRepository renterRepository) {
        this.adminRepository = adminRepository;
        this.propertyRepository = propertyRepository;
        this.ownerRepository = ownerRepository;
        this.renterRepository = renterRepository;
    }
    // Check if credentials match the single admin's username and password
    public boolean isSingleAdmin(String username, String rawPassword) {
        Admin admin = adminRepository.findFirstBy(); // Fetch the single admin
        return admin != null
                && admin.getUsername().equals(username)
                && passwordEncoder.matches(rawPassword, admin.getPassword());
    }

    public Admin findAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));
    }


    // Admin functionality
    public List<Property> findAllUnapprovedProperties() {
        return propertyRepository.findByApprovedFalse();
    }

    public Property approveProperty(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found with ID: " + propertyId));
        property.setApproved(true);
        return propertyRepository.save(property);
    }

    public void rejectProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }

//    public List<Owner> findAllApprovedOwners() {
//        return ownerRepository.findByApprovedTrue();
//    }

    public List<Renter> findAllApprovedRenters() {
        return renterRepository.findByApprovedTrue();
    }

    public Owner approveOwner(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found with ID: " + ownerId));
        owner.setApproved(true);
        return ownerRepository.save(owner);
    }

    public void rejectOwner(Long ownerId) {
        ownerRepository.deleteById(ownerId);
    }

    public Renter approveRenter(Long renterId) {
        Renter renter = renterRepository.findById(renterId)
                .orElseThrow(() -> new RuntimeException("Renter not found with ID: " + renterId));
        renter.setApproved(true);
        return renterRepository.save(renter);
    }

    public void rejectRenter(Long renterId) {
        renterRepository.deleteById(renterId);
    }
}
