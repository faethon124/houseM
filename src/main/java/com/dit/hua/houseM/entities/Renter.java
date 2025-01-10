package com.dit.hua.houseM.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("RENTER")
public class Renter extends BaseUser{
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinTable (
            name="renters_properties",
            joinColumns = @JoinColumn(name="renter_id"),
            inverseJoinColumns =
            @JoinColumn(name="properties_id"),
            uniqueConstraints =
                    {@UniqueConstraint(columnNames = {"properties_id","renter_id"})}
    )
    private List<Property> properties;
}
