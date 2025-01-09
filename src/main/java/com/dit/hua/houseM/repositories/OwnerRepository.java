package com.dit.hua.houseM.repositories;

import com.dit.hua.houseM.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    Optional<Owner> findByUsername(String username);

    Optional<Owner> findByEmail(String email);

    Optional<Owner> findByTpn(String tpn);

    List<Owner> findByApproved(boolean approved);

    List<Owner> findByApprovedTrue();
}

