package com.dit.hua.houseM.entities;

import jakarta.persistence.*;

@Entity
@Table(name="owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;


    @Column (name="password")
    private String password;

    @Column (name="username")
    private String username;

    @Column(name="TPN(afm)")
    private String TPN;
    //CONSTRUCTORS
    public Owner() {}

    public Owner(String firstName, String lastName, String email, String phone, String password, String username,String TPN) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.username = username;
        this.TPN = TPN;
    }


//SETTERS/GETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTPN() {
        return TPN;
    }

    public void setTPN(String TPN) {
        this.TPN = TPN;
    }

    //toString
    @Override
    public String toString(){
        return "Owner{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password=" + password + '\'' +
                ", username='" + username + '\'' +
                "TPN=" + TPN + '\'' +
                '}';
    }
}
