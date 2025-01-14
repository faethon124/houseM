package com.dit.hua.houseM.entities;

import jakarta.persistence.*;
import java.util.List;
@Entity
public class Owner extends BaseUser{
    @OneToMany(mappedBy = "owner",cascade = {CascadeType.ALL})
    private List<Property>properties;

    @OneToMany(mappedBy = "owner",cascade = {CascadeType.ALL})
    private List<ApplicationForm>Applicationforms;

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<ApplicationForm> getApplicationforms() {
        return Applicationforms;
    }

    public void setApplicationforms(List<ApplicationForm> applicationforms) {
        Applicationforms = applicationforms;
    }
}