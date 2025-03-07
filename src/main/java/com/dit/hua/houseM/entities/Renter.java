package com.dit.hua.houseM.entities;

import com.dit.hua.houseM.enums.Role;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Renter extends BaseUser {
    @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
    @JoinColumn(name = "Property_id")
    private Property properties;

    @OneToMany(mappedBy = "renter", cascade = CascadeType.ALL)
    private List<ApplicationForm> applicationForms;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_RENTER;

    public Renter() {
        super();
    }

    public Renter(String username, String email, String password) {
        super(username, email, password);
        this.role = Role.ROLE_OWNER;
    }

    public Renter(String firstName, String lastName, String username, String password, String email,
                  String tpn, String phone, boolean approved) {
        super(firstName, lastName, username, password, email, tpn, phone, approved);
        this.role = Role.ROLE_RENTER;
    }

    public Property getProperties() {
        return properties;
    }

    public void setProperties(Property properties) {
        this.properties = properties;
    }

    public List<ApplicationForm> getApplicationForms() {
        return applicationForms;
    }

    public void setApplicationForms(List<ApplicationForm> applicationForms) {
        this.applicationForms = applicationForms;
    }
}
