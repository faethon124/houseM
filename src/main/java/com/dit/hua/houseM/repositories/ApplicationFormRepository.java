package com.dit.hua.houseM.repositories;

import com.dit.hua.houseM.entities.ApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationFormRepository extends JpaRepository<ApplicationForm, Integer> {
    List<ApplicationForm> findByOwnerId(Long ownerId);
    List<ApplicationForm> findByRenterId(Long renterId);
    List<ApplicationForm> findByPropertyId(Long propertyId);
}

