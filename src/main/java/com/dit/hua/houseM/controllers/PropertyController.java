package com.dit.hua.houseM.controllers;

import com.dit.hua.houseM.entities.Property;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    private final PropertyService propertyService;

    public PropertyRestController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // GET /api/properties - List all properties
    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.findAll();
    }

    // GET /api/properties/{id} - Get a property by ID
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Property property = propertyService.findById(id);
        if (property == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(property);
    }

    // POST /api/properties - Add a new property
    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        if (property.getAddress() == null || property.getCity() == null || property.getZipcode() == null ||
                property.getArea() == null || property.getType() == null || property.getPrice() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Property savedProperty = propertyService.save(property);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProperty);
    }

    // PUT /api/properties/{id} - Update an existing property
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property updatedProperty) {
        Property existingProperty = propertyService.findById(id);
        if (existingProperty == null) {
            return ResponseEntity.notFound().build();
        }

        // Update fields
        if (updatedProperty.getAddress() != null) {
            existingProperty.setAddress(updatedProperty.getAddress());
        }
        if (updatedProperty.getCity() != null) {
            existingProperty.setCity(updatedProperty.getCity());
        }
        if (updatedProperty.getZipcode() != null) {
            existingProperty.setZipcode(updatedProperty.getZipcode());
        }
        if (updatedProperty.getArea() != null) {
            existingProperty.setArea(updatedProperty.getArea());
        }
        if (updatedProperty.getType() != null) {
            existingProperty.setType(updatedProperty.getType());
        }
        if (updatedProperty.getPrice() != null) {
            existingProperty.setPrice(updatedProperty.getPrice());
        }
        if (updatedProperty.getPhotoUrl() != null) {
            existingProperty.setPhotoUrl(updatedProperty.getPhotoUrl());
        }

        Property savedProperty = propertyService.save(existingProperty);
        return ResponseEntity.ok(savedProperty);
    }

    // DELETE /api/properties/{id} - Delete a property by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        Property existingProperty = propertyService.findById(id);
        if (existingProperty == null) {
            return ResponseEntity.notFound().build();
        }

        propertyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/properties/search/area - Get properties by area
    @GetMapping("/search/area")
    public List<Property> getPropertiesByArea(@RequestParam Double minArea, @RequestParam Double maxArea) {
        return propertyService.findByAreaRange(minArea, maxArea);
    }

    // POST /api/properties/{id}/upload-photo - Upload photo for a property
    @PostMapping("/{id}/upload-photo")
    public ResponseEntity<Property> uploadPhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Property property = propertyService.findById(id);
        if (property == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            // Simulating file upload logic
            String photoUrl = propertyService.uploadPhoto(file, id);
            property.setPhotoUrl(photoUrl);
            propertyService.save(property);
            return ResponseEntity.ok(property);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
