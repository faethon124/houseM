package com.dit.hua.houseM.entities;

import com.dit.hua.houseM.enums.ApplicationStatus;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="application_form")
public class ApplicationForm {
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

    @Column(name="TPN")
    private String TPN;

    @Column(name="date")
    private  Date  date;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "renter_id")
    private Renter renter;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "property_id")
    private Property property;

    public ApplicationForm(int id, String firstName, String lastName, String email, String phone, String username, String password, String TPN, Date date) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.TPN = TPN;
        this.date = date;

    }

    public ApplicationForm() {
    }

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

    public String getTPN() {
        return TPN;
    }

    public void setTPN(String TPN) {
        this.TPN = TPN;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "ApplicationForm{" +
                "id=" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", TPN='" + TPN + '\'' +
                ", date=" + date +
            '}';
    }
}

