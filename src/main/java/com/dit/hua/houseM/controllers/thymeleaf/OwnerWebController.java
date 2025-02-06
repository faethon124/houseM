package com.dit.hua.houseM.controllers.thymeleaf;

import com.dit.hua.houseM.entities.Owner;
import com.dit.hua.houseM.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerWebController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerWebController(OwnerService ownerService) {
        this.ownerService = ownerService;
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
}
