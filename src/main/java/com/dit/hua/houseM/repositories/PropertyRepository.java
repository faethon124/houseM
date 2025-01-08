package com.dit.hua.houseM.repositories;

import com.dit.hua.houseM.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByAreaBetween(Double minArea, Double maxArea);

    List<Property> findByApproved(boolean approved);

}