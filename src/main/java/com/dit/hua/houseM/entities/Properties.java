package com.dit.hua.houseM.entities;

import jakarta.persistence.*;

@Entity
@Table(name="properties")
public class Properties {
    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="zipcode")
    private String zipcode;

    @Column(name="size")
    private String size;

    @Column(name="type")
    private String type;

    @Column(name="price")
    private int price;

    public Properties(int id, String address, String city, String zipcode, String size, String type, int price) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.size = size;
        this.type = type;
        this.price = price;
    }
    public Properties() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "id=" + id + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", size='" + size + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
