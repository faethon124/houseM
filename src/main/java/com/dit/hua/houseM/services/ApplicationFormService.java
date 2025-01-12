package com.dit.hua.houseM.services;

import com.dit.hua.houseM.entities.ApplicationForm;
import com.dit.hua.houseM.repositories.ApplicationFormRepository;
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

    public ApplicationForm getApplicationFormById(int id) {
        return applicationFormRepository.findById(id).orElse(null);
    }

    public void deleteApplicationForm(int id) {
        applicationFormRepository.deleteById(id);
    }
}
