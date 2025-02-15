package com.dit.hua.houseM.controllers.thymeleaf;

import com.dit.hua.houseM.entities.ApplicationForm;
import com.dit.hua.houseM.entities.Owner;
import com.dit.hua.houseM.entities.Property;
import com.dit.hua.houseM.enums.ApplicationStatus;
import com.dit.hua.houseM.services.ApplicationFormService;
import com.dit.hua.houseM.services.OwnerService;
import com.dit.hua.houseM.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerWebController {

    private final OwnerService ownerService;
    private final PropertyService propertyService;
    private final ApplicationFormService applicationFormService;
    @Autowired
    public OwnerWebController(OwnerService ownerService,PropertyService propertyService,ApplicationFormService applicationFormService) {
        this.ownerService = ownerService;
        this.propertyService = propertyService;
        this.applicationFormService = applicationFormService;
    }
//    @GetMapping("/dashboard")
//    public String ownerDashboard(Model model) {
//        List<Property> properties = propertyService.findAll();  // Φέρνουμε όλα τα ακίνητα
//        model.addAttribute("properties", properties);
//        return "owner-dashboard"; // Φόρτωση του owner-dashboard.html
//    }
    @GetMapping("/dashboard")
    public String ownerDashboard(Model model, Principal principal) {
        Owner owner = ownerService.findByUsername(principal.getName()); // Επιστρέφει έναν Owner
        List<Property> properties = propertyService.findPropertiesByOwnerId(owner.getId());
        List<ApplicationForm> applications = applicationFormService.getApplicationFormsByOwner(owner.getId());

        model.addAttribute("properties", properties);
        model.addAttribute("applications", applications);
        return "owner-dashboard";
    }
//    @PostMapping("/add-property")
//    public String addProperty(@RequestParam String address,
//                              @RequestParam String city,
//                              @RequestParam String zipcode,
//                              @RequestParam int price,
//                              @RequestParam String Area,
//                              @RequestParam String type,
//                              @RequestParam boolean approved) {
//        Property property = new Property();
//        property.setAddress(address);
//        property.setCity(city);
//        property.setZipcode(zipcode);
//        property.setPrice(price);
//        property.setArea(Area);
//        property.setType(type);
//        property.setApproved(approved);
//        propertyService.save(property);
//
//        return "redirect:/owner/dashboard";  // Ανακατεύθυνση στο dashboard μετά την προσθήκη
//    }

//    @GetMapping("/find-property")
//    public String findProperty(@RequestParam Long id, Model model) {
//        Property property = propertyService.findById(id);
//        model.addAttribute("property", property);
//        return "owner-dashboard";  // Φόρτωση του dashboard με τα στοιχεία του ακινήτου
//    }

//    @PostMapping("/delete-property")
//    public String deleteProperty(@RequestParam Long id) {
//        propertyService.deleteById(id);
//        return "redirect:/owner/dashboard";  // Ανακατεύθυνση στο dashboard μετά τη διαγραφή
//    }


    @PostMapping("/add-property")
    public String addProperty(@ModelAttribute Property property, Principal principal) {
        Owner owner = ownerService.findByUsername(principal.getName());
        property.setOwner(owner); // Σύνδεση με τον owner
        propertyService.save(property);
        return "redirect:/owners/dashboard";
    }


    @PostMapping("/update-property")
    public String updateProperty(@RequestParam Long id, @ModelAttribute Property updatedProperty, Principal principal) {
        Owner owner = ownerService.findByUsername(principal.getName());
        Property existingProperty = propertyService.findById(id);

        if (!existingProperty.getOwner().getId().equals(owner.getId())) {
            return "redirect:/owners/dashboard?error=unauthorized"; // Αποτρέπει αλλαγές από άλλους
        }

        propertyService.update(id, updatedProperty);
        return "redirect:/owners/dashboard";
    }


    @PostMapping("/delete-property")
    public String deleteProperty(@RequestParam Long id, Principal principal) {
        Owner owner = ownerService.findByUsername(principal.getName());
        Property property = propertyService.findById(id);

        if (!property.getOwner().getId().equals(owner.getId())) {
            return "redirect:/owners/dashboard?error=unauthorized";
        }

        propertyService.deleteById(id);
        return "redirect:/owners/dashboard";
    }

    @GetMapping
    public String listOwners(Model model) {
        List<Owner> owners = ownerService.findAll();
        model.addAttribute("owners", owners);
        return "owners"; // This corresponds to the owners.html template
    }

    @GetMapping("/owner/dashboard")
    public String ownerDashboard() {
        return "owner-dashboard"; // Name of the Thymeleaf template (owner-dashboard.html)
    }

    @GetMapping("/search/by-first-name")
    public String getOwnersByFirstName(@RequestParam String firstName, Model model) {
        List<Owner> owners = ownerService.findByFirstNameOrderByFirstNameAsc(firstName);
        model.addAttribute("owners", owners);
        return "owners"; // This corresponds to the owners.html template
    }

    @GetMapping("/search/by-last-name")
    public String getOwnersByLastName(@RequestParam String lastName, Model model) {
        List<Owner> owners = ownerService.findByLastNameOrderByLastNameAsc(lastName);
        model.addAttribute("owners", owners);
        return "owners"; // This corresponds to the owners.html template
    }

    @PostMapping("/add")
    public ResponseEntity<String> addOwner(@RequestBody Owner owner) {
        ownerService.save(owner);
        return ResponseEntity.ok("Owner registered successfully!");
    }
//    @PostMapping("/owner/update-property")
//    public String updateProperty(@RequestParam Long id, @ModelAttribute Property updatedProperty) {
//        propertyService.update(id, updatedProperty);
//        return "redirect:/owner/dashboard";
//    }
    @PostMapping("/rental/status")
    public String changeApplicationStatus(@RequestParam int applicationId, @RequestParam ApplicationStatus status) {
    applicationFormService.updateApplicationStatus(applicationId, status);

    return "redirect:/owners/dashboard";
}

}
