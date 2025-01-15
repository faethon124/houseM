package com.dit.hua.houseM.repositories;


import com.dit.hua.houseM.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    // Custom query to find an admin by username
    Admin findFirstBy();
    Optional<Admin> findByUsername(String username);
}
