package com.dit.hua.houseM.controllers.rest;

import com.dit.hua.houseM.entities.Admin;
import com.dit.hua.houseM.entities.Owner;
import com.dit.hua.houseM.entities.Property;
import com.dit.hua.houseM.entities.Renter;
import com.dit.hua.houseM.services.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminRestController {

    private final AdminService adminService;

    public AdminRestController(AdminService adminService) {
        this.adminService = adminService;
    }

    // CRUD operations for admins
    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.findAllAdmins();
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Long id) {
        return adminService.findAdminById(id);
    }

    @PostMapping
    public Admin addAdmin(@RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }

    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        Admin existingAdmin = adminService.findAdminById(id);
        existingAdmin.setUsername(admin.getUsername());
        existingAdmin.setPassword(admin.getPassword());
        return adminService.saveAdmin(existingAdmin);
    }

    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return "Admin deleted successfully.";
    }

    // Admin functionality
    @GetMapping("/unapproved-properties")
    public List<Property> getAllUnapprovedProperties() {
        return adminService.findAllUnapprovedProperties();
    }

    @PostMapping("/approve-property/{propertyId}")
    public Property approveProperty(@PathVariable Long propertyId) {
        return adminService.approveProperty(propertyId);
    }

    @DeleteMapping("/reject-property/{propertyId}")
    public String rejectProperty(@PathVariable Long propertyId) {
        adminService.rejectProperty(propertyId);
        return "Property rejected and deleted.";
    }

    @GetMapping("/approved-owners")
    public List<Owner> getAllApprovedOwners() {
        return adminService.findAllApprovedOwners();
    }

    @GetMapping("/approved-renters")
    public List<Renter> getAllApprovedRenters() {
        return adminService.findAllApprovedRenters();
    }

    @PostMapping("/approve-owner/{ownerId}")
    public Owner approveOwner(@PathVariable Long ownerId) {
        return adminService.approveOwner(ownerId);
    }

    @DeleteMapping("/reject-owner/{ownerId}")
    public String rejectOwner(@PathVariable Long ownerId) {
        adminService.rejectOwner(ownerId);
        return "Owner rejected and deleted.";
    }

    @PostMapping("/approve-renter/{renterId}")
    public Renter approveRenter(@PathVariable Long renterId) {
        return adminService.approveRenter(renterId);
    }

    @DeleteMapping("/reject-renter/{renterId}")
    public String rejectRenter(@PathVariable Long renterId) {
        adminService.rejectRenter(renterId);
        return "Renter rejected and deleted.";
    }
}
