package com.dit.hua.houseM.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Owner")
public class Owner extends BaseUser{

}
