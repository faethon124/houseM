package com.dit.hua.houseM.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Owner extends BaseUser {
    @OneToMany(mappedBy = "owner")
    private List<Property> properties;

    // Getters and setters omitted for brevity
}