package com.dit.hua.houseM.controllers.rest;

import com.dit.hua.houseM.entities.Property;
import com.dit.hua.houseM.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyRestController {
    private final PropertyService propertyService;

    @Autowired
    public PropertyRestController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // Get all properties
    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.findAll();
    }

    // Get property by ID
    @GetMapping("/{id}")
    public Property getPropertyById(@PathVariable Long id) {
        return propertyService.findById(id);
    }

    // Get properties by area
    @GetMapping("/by-area")
    public List<Property> getPropertiesByArea(@RequestParam String area) {
        return propertyService.findByArea(area);
    }

    // **Get properties by city**
    @GetMapping("/by-city")
    public List<Property> getPropertiesByCity(@RequestParam String city) {
        return propertyService.findByCity(city);
    }

    // **Get all unapproved properties**
    @GetMapping("/unapproved")
    public List<Property> getUnapprovedProperties() {
        return propertyService.findUnapprovedProperties();
    }

    // Add a new property
    @PostMapping
    public Property addProperty(@RequestBody Property property) {
        System.out.println(property); // Log the incoming payload
        return propertyService.save(property);
    }

    // Update an existing property
    @PutMapping("/{id}")
    public Property updateProperty(@PathVariable Long id, @RequestBody Property property) {
        return propertyService.update(id, property);
    }

    // Delete a property
    @DeleteMapping("/{id}")
    public String deleteProperty(@PathVariable Long id) {
        propertyService.deleteById(id);
        return "Property deleted successfully.";
    }

    // Upload a photo for a property
    @PostMapping("/{id}/upload-photo")
    public String uploadPhoto(@PathVariable Long id, @RequestParam MultipartFile file) {
        propertyService.uploadPhoto(id, file);
        return "Photo uploaded successfully.";
    }
}