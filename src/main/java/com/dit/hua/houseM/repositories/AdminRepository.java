package com.dit.hua.houseM.repositories;


import com.dit.hua.houseM.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    // Custom query to find an admin by username
    Admin findByUsername(String username);
}
