package com.dit.hua.houseM.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Renter extends BaseUser{
   @OneToOne
   @JoinColumn(name = "Property_id")
    private Property properties;


    @OneToMany(mappedBy = "renter",cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE})
    private List<ApplicationForm>applicationForms;

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
