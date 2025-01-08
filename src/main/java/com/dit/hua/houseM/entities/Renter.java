package com.dit.hua.houseM.entities;

import jakarta.persistence.*;
@Entity
@DiscriminatorValue("Renter")
public class Renter extends BaseUser{

}
