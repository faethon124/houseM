package com.dit.hua.houseM.entities;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name="properties")
public class Property {
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

    @Column(name="area")
    private String area;

    @Column(name="type")
    private String type;

    @Column(name="price")
    private int price;

    @Column(name = "approved", nullable = false)
    private boolean approved = false;

    @Lob
    @Column(name = "photos")
    private byte[] photos; // For storing property photos

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToOne
    @JoinColumn(name = "renter_id")
    private Renter renters;


    @OneToMany(mappedBy = "property",cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE})
    private List<ApplicationForm>applicationForms;

    public Property(int id, String address, String city, String zipcode, String size, String type, int price) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.area = area;
        this.type = type;
        this.price = price;
        this.approved = false;
    }
    public Property() {}

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public byte[] getPhotos() {
        return photos;
    }

    public void setPhotos(byte[] photos) {
        this.photos = photos;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Renter getRenters() {
        return renters;
    }

    public void setRenters(Renter renters) {
        this.renters = renters;
    }

    public List<ApplicationForm> getApplicationForms() {
        return applicationForms;
    }

    public void setApplicationForms(List<ApplicationForm> applicationForms) {
        this.applicationForms = applicationForms;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "id=" + id + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", size='" + area + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
