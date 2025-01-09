package com.dit.hua.houseM.entities;

import jakarta.persistence.*;
@Entity
@DiscriminatorValue("RENTER")
public class Renter extends BaseUser{

}
