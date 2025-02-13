package com.dit.hua.houseM.repositories;

import com.dit.hua.houseM.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Optional<Owner> findByUsername(String username);
    List<Owner> findByFirstNameOrderByFirstNameAsc(String firstName);

    List<Owner> findByLastNameOrderByLastNameAsc(String lastName);

    List<Owner> findByUsernameOrderByUsernameAsc(String username);
}

