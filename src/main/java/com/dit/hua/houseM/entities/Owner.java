package com.dit.hua.houseM.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("OWNER")
public class Owner extends BaseUser{

}
