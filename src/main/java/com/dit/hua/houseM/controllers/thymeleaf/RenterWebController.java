package com.dit.hua.houseM.controllers.thymeleaf;

import com.dit.hua.houseM.entities.ApplicationForm;
import com.dit.hua.houseM.entities.Renter;
import com.dit.hua.houseM.entities.Property;
import com.dit.hua.houseM.services.ApplicationFormService;
import com.dit.hua.houseM.services.PropertyService;
import com.dit.hua.houseM.services.RenterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/renters")
public class RenterWebController {

    private final RenterService renterService;
    private final PropertyService propertyService;
    private final ApplicationFormService applicationFormService;

    @Autowired
    public RenterWebController(RenterService renterService, PropertyService propertyService, ApplicationFormService applicationFormService) {
        this.renterService = renterService;
        this.propertyService = propertyService;
        this.applicationFormService = applicationFormService;
    }

    // Dashboard for Renter
    @GetMapping("/dashboard")
    public String renterDashboard(Model model, Principal principal) {
        Renter renter = renterService.findByUsername(principal.getName());
        List<Property> properties = propertyService.findAll();
        List<ApplicationForm> applications = applicationFormService.getApplicationFormsByRenter(renter.getId());

        model.addAttribute("properties", properties);
        model.addAttribute("applications", applications);
        return "renter-dashboard";
    }

    // Add an Application Form
    @PostMapping("/add-application")
    public String addApplicationForm(@ModelAttribute ApplicationForm applicationForm, Principal principal) {
        Renter renter = renterService.findByUsername(principal.getName());
        applicationForm.setRenter(renter); // Link to the renter
        applicationFormService.saveApplicationForm(applicationForm);
        return "redirect:/renters/dashboard"; // Redirect to renter dashboard after adding
    }

    // Update Application Form (status change or details)
    @PostMapping("/update-application")
    public String updateApplicationForm(@RequestParam int applicationId,
                                        @ModelAttribute ApplicationForm updatedApplicationForm,
                                        Principal principal) {
        Renter renter = renterService.findByUsername(principal.getName());
        ApplicationForm existingApplication = applicationFormService.getApplicationFormById(applicationId);


        if (existingApplication == null || !existingApplication.getRenter().getId().equals(renter.getId())) {
            return "redirect:/renters/dashboard?error=unauthorized";
        }


        updatedApplicationForm.setStatus(existingApplication.getStatus());


        existingApplication.setProperty(updatedApplicationForm.getProperty());


        // existingApplication.setOtherField(updatedApplicationForm.getOtherField());

        applicationFormService.saveApplicationForm(existingApplication);

        return "redirect:/renters/dashboard";
    }

    // Delete an Application Form
    @PostMapping("/delete-application")
    public String deleteApplicationForm(@RequestParam int applicationId) {
        applicationFormService.deleteApplicationForm(applicationId); // Delete the application form
        return "redirect:/renters/dashboard"; // Redirect back to dashboard
    }
}
