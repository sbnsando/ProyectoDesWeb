package edu.javeriana.tools.entity;

import jakarta.persistence.*;

/**
 * Entidad que representa una herramienta.
 */
@Entity
@Table(name = "tools")
public class Tool {

    /**
     * Constructor por defecto de la clase Tool.
     */
    public Tool() {
    }

    /**
     * Constructor de la clase Tool con los datos de la herramienta.
     *
     * @param idBrand     El ID de la marca de la herramienta.
     * @param name        El nombre de la herramienta.
     * @param description La descripción de la herramienta.
     * @param price       El precio de la herramienta.
     * @param img         La imagen de la herramienta.
     * @param country     El país de origen de la herramienta.
     * @param cities      Las ciudades en las que se encuentra disponible la herramienta.
     * @param quantity    La cantidad disponible de la herramienta.
     */
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

    /**
     * Obtiene el ID de la herramienta.
     *
     * @return El ID de la herramienta.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID de la herramienta.
     *
     * @param id El ID de la herramienta.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el país de origen de la herramienta.
     *
     * @return El país de origen de la herramienta.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Establece el país de origen de la herramienta.
     *
     * @param country El país de origen de la herramienta.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Obtiene el ID de la marca de la herramienta.
     *
     * @return El ID de la marca de la herramienta.
     */
    public int getIdBrand() {
        return idBrand;
    }

    /**
     * Establece el ID de la marca de la herramienta.
     *
     * @param idBrand El ID de la marca de la herramienta.
     */
    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    /**
     * Obtiene el nombre de la herramienta.
     *
     * @return El nombre de la herramienta.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la herramienta.
     *
     * @param name El nombre de la herramienta.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la descripción de la herramienta.
     *
     * @return La descripción de la herramienta.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción de la herramienta.
     *
     * @param description La descripción de la herramienta.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene el precio de la herramienta.
     *
     * @return El precio de la herramienta.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Establece el precio de la herramienta.
     *
     * @param price El precio de la herramienta.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Obtiene la imagen de la herramienta.
     *
     * @return La imagen de la herramienta.
     */
    public String getImg() {
        return img;
    }

    /**
     * Establece la imagen de la herramienta.
     *
     * @param img La imagen de la herramienta.
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * Obtiene las ciudades en las que se encuentra disponible la herramienta.
     *
     * @return Las ciudades en las que se encuentra disponible la herramienta.
     */
    public String getCities() {
        return cities;
    }

    /**
     * Establece las ciudades en las que se encuentra disponible la herramienta.
     *
     * @param cities Las ciudades en las que se encuentra disponible la herramienta.
     */
    public void setCities(String cities) {
        this.cities = cities;
    }

    /**
     * Obtiene la cantidad disponible de la herramienta.
     *
     * @return La cantidad disponible de la herramienta.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Establece la cantidad disponible de la herramienta.
     *
     * @param quantity La cantidad disponible de la herramienta.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
