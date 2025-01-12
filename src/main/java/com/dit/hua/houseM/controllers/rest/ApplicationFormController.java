package com.dit.hua.houseM.controllers;

import com.dit.hua.houseM.entities.ApplicationForm;
import com.dit.hua.houseM.services.ApplicationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/application-forms")
public class ApplicationFormController {

    private final ApplicationFormService applicationFormService;

    @Autowired
    public ApplicationFormController(ApplicationFormService applicationFormService) {
        this.applicationFormService = applicationFormService;
    }

    // Submit a new application form (Renter action)
    @PostMapping
    public ResponseEntity<ApplicationForm> submitApplicationForm(@RequestBody ApplicationForm applicationForm) {
        ApplicationForm savedForm = applicationFormService.saveApplicationForm(applicationForm);
        return ResponseEntity.ok(savedForm);
    }

    // Retrieve all application forms (Owner action)
    @GetMapping
    public ResponseEntity<List<ApplicationForm>> getAllApplicationForms() {
        List<ApplicationForm> forms = applicationFormService.getAllApplicationForms();
        return ResponseEntity.ok(forms);
    }

    // Retrieve application forms by owner
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ApplicationForm>> getApplicationFormsByOwner(@PathVariable Long ownerId) {
        List<ApplicationForm> forms = applicationFormService.getApplicationFormsByOwner(ownerId);
        return ResponseEntity.ok(forms);
    }

    // Retrieve a specific application form by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationForm> getApplicationFormById(@PathVariable int id) {
        ApplicationForm form = applicationFormService.getApplicationFormById(id);
        return ResponseEntity.ok(form);
    }

    // Delete an application form
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplicationForm(@PathVariable int id) {
        applicationFormService.deleteApplicationForm(id);
        return ResponseEntity.noContent().build();
    }
}

