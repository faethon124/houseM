package com.dit.hua.houseM.services;

import com.dit.hua.houseM.entities.ApplicationForm;
import com.dit.hua.houseM.entities.Property;
import com.dit.hua.houseM.enums.ApplicationStatus;
import com.dit.hua.houseM.repositories.ApplicationFormRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationFormService {

    private final ApplicationFormRepository applicationFormRepository;

    @Autowired
    public ApplicationFormService(ApplicationFormRepository applicationFormRepository) {
        this.applicationFormRepository = applicationFormRepository;
    }

    public ApplicationForm saveApplicationForm(ApplicationForm form) {
        return applicationFormRepository.save(form);
    }

    public List<ApplicationForm> getAllApplicationForms() {
        return applicationFormRepository.findAll();
    }

    public List<ApplicationForm> getApplicationFormsByOwner(Long ownerId) {
        return applicationFormRepository.findByOwnerId(ownerId);
    }
    public List<ApplicationForm> getApplicationFormsByRenter(Long renterId) {
            return applicationFormRepository.findByRenterId(renterId);
    }
    public ApplicationForm getApplicationFormById(int id) {
        return applicationFormRepository.findById(id).orElse(null);
    }

    public void deleteApplicationForm(int id) {
        applicationFormRepository.deleteById(id);
    }



    @Transactional
    public void updateApplicationStatus(int applicationId, ApplicationStatus status) {
        applicationFormRepository.findById(applicationId).ifPresent(application -> {
            application.setStatus(status);
            applicationFormRepository.save(application);
        });
    }
}
