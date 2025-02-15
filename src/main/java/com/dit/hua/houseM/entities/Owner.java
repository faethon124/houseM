package com.dit.hua.houseM.entities;

import com.dit.hua.houseM.enums.Role;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Owner extends BaseUser {
    @OneToMany(mappedBy = "owner", cascade = {CascadeType.ALL})
    private List<Property> properties;

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.ALL})
    private List<ApplicationForm> applicationForms;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_OWNER;

    public Owner() {
        super();
    }

    public Owner(String username, String email, String password) {
        super(username, email, password);
        this.role = Role.ROLE_OWNER;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<ApplicationForm> getApplicationForms() {
        return applicationForms;
    }

    public void setApplicationForms(List<ApplicationForm> applicationForms) {
        this.applicationForms = applicationForms;
    }
}
