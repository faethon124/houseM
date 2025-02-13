package com.dit.hua.houseM.repositories;

import com.dit.hua.houseM.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property,Long> {

    // Custom methods
    List<Property> findByArea(String area);

    List<Property> findByCity(String city);

    List<Property> findByApprovedFalse(); // For admin to fetch unapproved properties

    List<Property> findByOwnerId(Long ownerId);
}
