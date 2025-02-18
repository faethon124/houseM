package com.dit.hua.houseM.repositories;

import com.dit.hua.houseM.entities.ApplicationForm;
import com.dit.hua.houseM.entities.Property;
import com.dit.hua.houseM.entities.Renter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationFormRepository extends JpaRepository<ApplicationForm, Integer> {
    List<ApplicationForm> findByOwnerId(Long ownerId);
    List<ApplicationForm> findByRenterId(Long renterId);
    List<ApplicationForm> findByPropertyId(Long propertyId);
    Optional<ApplicationForm> findByRenterAndProperty(Renter renter, Property property);
}

