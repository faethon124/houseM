package com.dit.hua.houseM.services;

import com.dit.hua.houseM.entities.Property;
import com.dit.hua.houseM.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    // Fetch all properties
    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    // Find property by ID
    public Property findById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with ID: " + id));
    }

    // Find properties by area
    public List<Property> findByArea(String area) {
        return propertyRepository.findByArea(area);
    }

    // **Find properties by city**
    public List<Property> findByCity(String city) {
        return propertyRepository.findByCity(city);
    }

    // **Find unapproved properties**
    public List<Property> findUnapprovedProperties() {
        return propertyRepository.findByApprovedFalse();
    }

    // Save a new or updated property
    public Property save(Property property) {
        return propertyRepository.save(property);
    }

    // Update property details
    public Property update(Long id, Property updatedProperty) {
        Property existingProperty = findById(id);
        existingProperty.setAddress(updatedProperty.getAddress());
        existingProperty.setCity(updatedProperty.getCity());
        existingProperty.setZipcode(updatedProperty.getZipcode());
        existingProperty.setArea(updatedProperty.getArea());
        existingProperty.setType(updatedProperty.getType());
        existingProperty.setPrice(updatedProperty.getPrice());
        existingProperty.setApproved(updatedProperty.getApproved());
        return propertyRepository.save(existingProperty);
    }

    // Delete property by ID
    public void deleteById(Long id) {
        propertyRepository.deleteById(id);
    }

    // Upload property photo
    public void uploadPhoto(Long id, MultipartFile file) {
        // Logic to save photo and associate it with property
        Property property = findById(id);
        // Save photo in database/storage and link it to the property
    }
}
