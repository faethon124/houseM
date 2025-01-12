package com.dit.hua.houseM.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("RENTER")
public class Renter extends BaseUser{
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinTable (
            name="renters_properties",
            joinColumns = @JoinColumn(name="Renter_id"),
            inverseJoinColumns =
            @JoinColumn(name="Property_id"),
            uniqueConstraints =
                    {@UniqueConstraint(columnNames = {"Property_id","Renter_id"})}
    )
    private List<Property> properties;
}
