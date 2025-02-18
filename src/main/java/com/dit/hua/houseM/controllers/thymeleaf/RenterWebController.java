//package com.dit.hua.houseM.controllers.thymeleaf;
//
//import com.dit.hua.houseM.entities.ApplicationForm;
//import com.dit.hua.houseM.entities.Renter;
//import com.dit.hua.houseM.entities.Property;
//import com.dit.hua.houseM.enums.ApplicationStatus;
//import com.dit.hua.houseM.services.ApplicationFormService;
//import com.dit.hua.houseM.services.PropertyService;
//import com.dit.hua.houseM.services.RenterService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//
//import java.security.Principal;
//import java.util.Date;
//import java.util.List;
//
//@Controller
//@RequestMapping("/renters")
//public class RenterWebController {
//
//    private final RenterService renterService;
//    private final PropertyService propertyService;
//    private final ApplicationFormService applicationFormService;
//
//    @Autowired
//    public RenterWebController(RenterService renterService, PropertyService propertyService, ApplicationFormService applicationFormService) {
//        this.renterService = renterService;
//        this.propertyService = propertyService;
//        this.applicationFormService = applicationFormService;
//    }
//
//    // Dashboard for Renter
//    @GetMapping("/dashboard")
//    public String renterDashboard(Model model, Principal principal) {
//        Renter renter = renterService.findByUsername(principal.getName());
//        List<Property> properties = propertyService.findAll();
//        List<ApplicationForm> applications = applicationFormService.getApplicationFormsByRenter(renter.getId());
//
//        model.addAttribute("properties", properties);
//        model.addAttribute("applications", applications);
//        return "renter-dashboard";
//    }
//
//    @PostMapping("/add-application")
//    public String addApplicationForm(@RequestParam Long propertyId,
//                                     @RequestParam String applicationFormdate,
//                                     @RequestParam String TPN,
//                                     Principal principal) {
//        try {
//            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//            Date parsedDate = formatter.parse(applicationFormdate);
//
//            Renter renter = renterService.findByUsername(principal.getName());
//            Property property = propertyService.findById(propertyId);
//
//            if (property == null) {
//                return "redirect:/renters/dashboard?error=propertyNotFound";
//            }
//
//            ApplicationForm applicationForm = new ApplicationForm();
//            applicationForm.setRenter(renter);
//            applicationForm.setProperty(property);
//            applicationForm.setDate(parsedDate);
//            applicationForm.setTPN(TPN);
//            applicationForm.setStatus(ApplicationStatus.PENDING);
//
//            applicationFormService.saveApplicationForm(applicationForm);
//            return "redirect:/renters/dashboard";
//
//        } catch (ParseException e) {
//            return "redirect:/renters/dashboard?error=invalidDate";
//        }
//        }
//
//    @PostMapping("/update-application")
//    public String updateApplicationForm(@RequestParam int applicationId,
//                                        @RequestParam Date applicationFormdate,
//                                        @RequestParam String TPN,
//                                        Principal principal) {
//        Renter renter = renterService.findByUsername(principal.getName());
//        ApplicationForm existingApplication = applicationFormService.getApplicationFormById(applicationId);
//
//        if (existingApplication == null || !existingApplication.getRenter().getId().equals(renter.getId())) {
//            return "redirect:/renters/dashboard?error=unauthorized";
//        }
//
//        existingApplication.setDate(applicationFormdate);
//        existingApplication.setTPN(TPN);
//        applicationFormService.saveApplicationForm(existingApplication);
//
//        return "redirect:/renters/dashboard";
//    }
//
//    @PostMapping("/delete-application")
//    public String deleteApplicationForm(@RequestParam int applicationId, Principal principal) {
//        Renter renter = renterService.findByUsername(principal.getName());
//        ApplicationForm existingApplication = applicationFormService.getApplicationFormById(applicationId);
//
//        if (existingApplication == null || !existingApplication.getRenter().getId().equals(renter.getId())) {
//            return "redirect:/renters/dashboard?error=unauthorized";
//        }
//
//        applicationFormService.deleteApplicationForm(applicationId);
//        return "redirect:/renters/dashboard";
//    }
//    // Add an Application Form
////    @PostMapping("/add-application")
////    public String addApplicationForm(@ModelAttribute ApplicationForm applicationForm, Principal principal) {
////        Renter renter = renterService.findByUsername(principal.getName());
////        applicationForm.setRenter(renter); // Link to the renter
////        applicationFormService.saveApplicationForm(applicationForm);
////        return "redirect:/renters/dashboard"; // Redirect to renter dashboard after adding
////    }
////    @PostMapping("/add-application")
////    public String addApplicationForm(@ModelAttribute ApplicationForm applicationForm,
////                                     @RequestParam Long propertyId,
////                                     Principal principal) {
////        Renter renter = renterService.findByUsername(principal.getName());
////        Property property = propertyService.findById(propertyId);
////
////        applicationForm.setRenter(renter);
////        applicationForm.setProperty(property);
////
////        applicationFormService.saveApplicationForm(applicationForm);
////
////        return "redirect:/renters/dashboard";
////    }
////
////
//////    // Update Application Form (status change or details)
//////    @PostMapping("/update-application")
//////    public String updateApplicationForm(@RequestParam int applicationId,
//////                                        @ModelAttribute ApplicationForm updatedApplicationForm,
//////                                        Principal principal) {
//////        Renter renter = renterService.findByUsername(principal.getName());
//////        ApplicationForm existingApplication = applicationFormService.getApplicationFormById(applicationId);
//////
//////
//////        if (existingApplication == null || !existingApplication.getRenter().getId().equals(renter.getId())) {
//////            return "redirect:/renters/dashboard?error=unauthorized";
//////        }
//////
//////
//////        updatedApplicationForm.setStatus(existingApplication.getStatus());
//////
//////
//////        existingApplication.setProperty(updatedApplicationForm.getProperty());
//////
//////
//////        // existingApplication.setOtherField(updatedApplicationForm.getOtherField());
//////
//////        applicationFormService.saveApplicationForm(existingApplication);
//////
//////        return "redirect:/renters/dashboard";
//////    }
////
////    @PostMapping("/update-application")
////    public String updateApplicationForm(@RequestParam int applicationId,
////                                        @ModelAttribute ApplicationForm updatedApplicationForm,
////                                        Principal principal) {
////        Renter renter = renterService.findByUsername(principal.getName());
////        ApplicationForm existingApplication = applicationFormService.getApplicationFormById(applicationId);
////
////        if (existingApplication == null || !existingApplication.getRenter().getId().equals(renter.getId())) {
////            return "redirect:/renters/dashboard?error=unauthorized";
////        }
////
////        // Δεν αλλάζουμε το status
////        existingApplication.setDate(updatedApplicationForm.getDate());
////        existingApplication.setTPN(updatedApplicationForm.getTPN());
////
////        applicationFormService.saveApplicationForm(existingApplication);
////
////        return "redirect:/renters/dashboard";
////    }
////
////    // Delete an Application Form
////    @PostMapping("/delete-application")
////    public String deleteApplicationForm(@RequestParam int applicationId) {
////        applicationFormService.deleteApplicationForm(applicationId); // Delete the application form
////        return "redirect:/renters/dashboard"; // Redirect back to dashboard
////    }
//}
//package com.dit.hua.houseM.controllers.thymeleaf;
//
//import com.dit.hua.houseM.entities.ApplicationForm;
//import com.dit.hua.houseM.entities.Property;
//import com.dit.hua.houseM.entities.Renter;
//import com.dit.hua.houseM.enums.ApplicationStatus;
//import com.dit.hua.houseM.services.ApplicationFormService;
//import com.dit.hua.houseM.services.PropertyService;
//import com.dit.hua.houseM.services.RenterService;
//
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//@Controller
//@RequestMapping("/renters")
//public class RenterWebController {
//    private static final Logger logger = LoggerFactory.getLogger(RenterWebController.class);
//    private final RenterService renterService;
//    private final PropertyService propertyService;
//    private final ApplicationFormService applicationFormService;
//
//    public RenterWebController(RenterService renterService, PropertyService propertyService,
//                               ApplicationFormService applicationFormService) {
//        this.renterService = renterService;
//        this.propertyService = propertyService;
//        this.applicationFormService = applicationFormService;
//    }
//
//    @GetMapping("/dashboard")
//    public String renterDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
//        Renter renter = renterService.findByUsername(userDetails.getUsername());
//        List<Property> properties = propertyService.findAll();
//        List<ApplicationForm> applications = applicationFormService.getApplicationFormsByRenter(renter.getId());
//
//        model.addAttribute("username", userDetails.getUsername());
//        model.addAttribute("properties", properties);
//        model.addAttribute("applications", applications);
//        return "renter-dashboard";
//    }
//
//    @PostMapping("/apply")
//    public String applyForProperty(@RequestParam Long propertyId,
//                                   @RequestParam String applicationFormdate,
//                                   @RequestParam String TPN,
//                                   @AuthenticationPrincipal UserDetails userDetails,
//                                   RedirectAttributes redirectAttributes) {
//        try {
//            logger.info("Received request to apply for propertyId: {}", propertyId);
//
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            Date parsedDate = formatter.parse(applicationFormdate);
//
//            Renter renter = renterService.findByUsername(userDetails.getUsername());
//            Property property = propertyService.findById(propertyId);
//
//            if (property == null) {
//                logger.error("Property not found for id: {}", propertyId);
//                redirectAttributes.addFlashAttribute("errorMessage", "Property not found.");
//                return "redirect:/renters/dashboard";
//            }
//
//            ApplicationForm existingApplication = applicationFormService.getApplicationByRenterAndProperty(renter, property);
//            if (existingApplication != null) {
//                logger.error("You have already applied for this property: {}", propertyId);
//                redirectAttributes.addFlashAttribute("errorMessage", "You have already applied for this property.");
//                return "redirect:/renters/dashboard";
//            }
//
//            ApplicationForm applicationForm = new ApplicationForm();
//            applicationForm.setRenter(renter);
//            applicationForm.setProperty(property);
//            applicationForm.setDate(parsedDate);
//            applicationForm.setTPN(TPN);
//            applicationForm.setStatus(ApplicationStatus.PENDING);
//
//            applicationFormService.saveApplicationForm(applicationForm);
//            return "redirect:/renters/dashboard";
//
//        } catch (ParseException e) {
//            logger.error("Error parsing the application date", e);
//            redirectAttributes.addFlashAttribute("errorMessage", "Invalid date format.");
//            return "redirect:/renters/dashboard";
//        }
//    }
//
//
//
//    @PostMapping("/update-application")
//    public String updateApplicationForm(@RequestParam int applicationId,
//                                        @RequestParam String applicationFormDate,
//                                        @RequestParam String TPN,
//                                        @AuthenticationPrincipal UserDetails userDetails,
//                                        RedirectAttributes redirectAttributes) {
//        try {
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            Date parsedDate = formatter.parse(applicationFormDate);
//
//            Renter renter = renterService.findByUsername(userDetails.getUsername());
//            ApplicationForm existingApplication = applicationFormService.getApplicationFormById(applicationId);
//
//            if (existingApplication == null || !existingApplication.getRenter().getId().equals(renter.getId())) {
//                redirectAttributes.addFlashAttribute("errorMessage", "Unauthorized action.");
//                return "redirect:/renters/dashboard";
//            }
//
//            existingApplication.setDate(parsedDate);
//            existingApplication.setTPN(TPN);
//            applicationFormService.saveApplicationForm(existingApplication);
//
//            return "redirect:/renters/dashboard";
//        } catch (ParseException e) {
//            redirectAttributes.addFlashAttribute("errorMessage", "Invalid date format.");
//            return "redirect:/renters/dashboard";
//        }
//    }
//
//    @PostMapping("/delete-application")
//    public String deleteApplicationForm(@RequestParam int applicationId,
//                                        @AuthenticationPrincipal UserDetails userDetails,
//                                        RedirectAttributes redirectAttributes) {
//        Renter renter = renterService.findByUsername(userDetails.getUsername());
//        ApplicationForm existingApplication = applicationFormService.getApplicationFormById(applicationId);
//
//        if (existingApplication == null || !existingApplication.getRenter().getId().equals(renter.getId())) {
//            redirectAttributes.addFlashAttribute("errorMessage", "Unauthorized action.");
//            return "redirect:/renters/dashboard";
//        }
//
//        applicationFormService.deleteApplicationForm(applicationId);
//        return "redirect:/renters/dashboard";
//    }
//}

package com.dit.hua.houseM.controllers.thymeleaf;

import com.dit.hua.houseM.entities.ApplicationForm;
import com.dit.hua.houseM.entities.Property;
import com.dit.hua.houseM.entities.Renter;
import com.dit.hua.houseM.enums.ApplicationStatus;
import com.dit.hua.houseM.services.ApplicationFormService;
import com.dit.hua.houseM.services.PropertyService;
import com.dit.hua.houseM.services.RenterService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/renters")
public class RenterWebController {

    private static final Logger logger = LoggerFactory.getLogger(RenterWebController.class);
    private final RenterService renterService;
    private final PropertyService propertyService;
    private final ApplicationFormService applicationFormService;

    public RenterWebController(RenterService renterService, PropertyService propertyService,
                               ApplicationFormService applicationFormService) {
        this.renterService = renterService;
        this.propertyService = propertyService;
        this.applicationFormService = applicationFormService;
    }

    // ** Dashboard View ** - To render the Renter Dashboard
    @GetMapping("/dashboard")
    public String renterDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        // Retrieve the current logged-in renter
        Renter renter = renterService.findByUsername(userDetails.getUsername());

        // Fetch properties and renter's applications
        List<Property> properties = propertyService.findAll();
        List<ApplicationForm> applications = applicationFormService.getApplicationFormsByRenter(renter.getId());

        // Add attributes to the model
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("properties", properties);
        model.addAttribute("applications", applications);
        return "renter-dashboard";  // Thymeleaf template
    }

    // ** Apply for Property ** - To submit a new application for a property
    @PostMapping("/apply")
    public String applyForProperty(
            @RequestParam("propertyId") Long propertyId,  // ΕΔΩ ΠΡΕΠΕΙ ΝΑ ΕΧΕΙ ΙΔΙΟ ΟΝΟΜΑ ΜΕ ΤΗ ΦΟΡΜΑ
            @RequestParam("applicationFormdate") String applicationFormdate,
            @RequestParam("TPN") String TPN,
            @AuthenticationPrincipal UserDetails userDetails,
            RedirectAttributes redirectAttributes) {
        try {
            logger.info("Received request to apply for propertyId: {}", propertyId);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = formatter.parse(applicationFormdate);

            Renter renter = renterService.findByUsername(userDetails.getUsername());
            Property property = propertyService.findById(propertyId);

            if (property == null) {
                logger.error("Property not found for id: {}", propertyId);
                redirectAttributes.addFlashAttribute("errorMessage", "Property not found.");
                return "redirect:/renters/dashboard";
            }

            ApplicationForm existingApplication = applicationFormService.getApplicationByRenterAndProperty(renter, property);
            if (existingApplication != null) {
                logger.error("You have already applied for this property: {}", propertyId);
                redirectAttributes.addFlashAttribute("errorMessage", "You have already applied for this property.");
                return "redirect:/renters/dashboard";
            }

            ApplicationForm applicationForm = new ApplicationForm();
            applicationForm.setRenter(renter);
            applicationForm.setProperty(property);
            applicationForm.setDate(parsedDate);
            applicationForm.setTPN(TPN);
            applicationForm.setStatus(ApplicationStatus.PENDING);

            applicationFormService.saveApplicationForm(applicationForm);
            return "redirect:/renters/dashboard";

        } catch (ParseException e) {
            logger.error("Error parsing the application date", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid date format.");
            return "redirect:/renters/dashboard";
        }
    }


    // ** Update Application ** - To update application details like date or TPN
    @PostMapping("/update-application")
    public String updateApplicationForm(@RequestParam int applicationId,
                                        @RequestParam String applicationFormDate,
                                        @RequestParam String TPN,
                                        @AuthenticationPrincipal UserDetails userDetails,
                                        RedirectAttributes redirectAttributes) {
        try {
            // Parse the date
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = formatter.parse(applicationFormDate);

            // Retrieve the renter and application
            Renter renter = renterService.findByUsername(userDetails.getUsername());
            ApplicationForm existingApplication = applicationFormService.getApplicationFormById(applicationId);

            // Ensure the application belongs to the current renter
            if (existingApplication == null || !existingApplication.getRenter().getId().equals(renter.getId())) {
                redirectAttributes.addFlashAttribute("errorMessage", "Unauthorized action.");
                return "redirect:/renters/dashboard";
            }

            // Update the application with new data
            existingApplication.setDate(parsedDate);
            existingApplication.setTPN(TPN);
            applicationFormService.saveApplicationForm(existingApplication);

            return "redirect:/renters/dashboard";  // Redirect back to dashboard
        } catch (ParseException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid date format.");
            return "redirect:/renters/dashboard";
        }
    }

    // ** Delete Application ** - To delete a specific application
    @PostMapping("/delete-application")
    public String deleteApplicationForm(@RequestParam int applicationId,
                                        @AuthenticationPrincipal UserDetails userDetails,
                                        RedirectAttributes redirectAttributes) {
        // Retrieve the renter and application
        Renter renter = renterService.findByUsername(userDetails.getUsername());
        ApplicationForm existingApplication = applicationFormService.getApplicationFormById(applicationId);

        // Ensure the application belongs to the current renter
        if (existingApplication == null || !existingApplication.getRenter().getId().equals(renter.getId())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Unauthorized action.");
            return "redirect:/renters/dashboard";
        }

        // Delete the application
        applicationFormService.deleteApplicationForm(applicationId);

        return "redirect:/renters/dashboard";  // Redirect back to dashboard
    }
}
