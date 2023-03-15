package edu.javeriana.tools.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tools")
public class Tool {

    public Tool() {
    }

    public Tool(int idBrand, String name, String description, int price, String img, String country, String cities, int quantity) {
        this.idBrand = idBrand;
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
        this.country = country;
        this.cities = cities;
        this.quantity = quantity;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_brand")
    private int idBrand;

    @Column(name = "name")
    private  String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "img")
    private String img;

    @Column(name = "country")
    private String country;

    @Column(name = "cities")
    private String cities;

    @Column(name = "quantity")
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String listIdCities) {
        this.cities = listIdCities;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
