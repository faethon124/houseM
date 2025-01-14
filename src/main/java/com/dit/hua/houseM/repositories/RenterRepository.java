package com.dit.hua.houseM.repositories;

import com.dit.hua.houseM.entities.Renter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RenterRepository extends JpaRepository<Renter, Long> {
    // Find renters by username
    Optional<Renter> findByUsername(String username);
    // Find renters by email
    List<Renter> findByEmail(String email);

    // Find renters by tax number (TPN)
    List<Renter> findByTpn(String tpn);

    List<Renter> findByApprovedTrue();
}
