package edu.javeriana.tools.dto;

public class ToolDto {

    private int idBrand;

    private  String name;

    private String description;

    private int price;

    private String img;

    private String country;

    private String cities;

    private int quantity;

    public ToolDto(){
    }

    public ToolDto(int idBrand, String name, String description, int price, String img, String country, String cities, int quantity) {
        this.idBrand = idBrand;
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
        this.country = country;
        this.cities = cities;
        this.quantity = quantity;
    }

    public int getIdBrand() {
        return idBrand;
    }

    public void setBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
