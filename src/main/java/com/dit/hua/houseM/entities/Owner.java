package com.dit.hua.houseM.entities;

import jakarta.persistence.*;
import java.util.List;
@Entity
public class Owner extends BaseUser{
   @OneToMany(mappedBy = "owner",cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE})
    private List<Property>properties;

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}