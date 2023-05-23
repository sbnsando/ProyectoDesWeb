package edu.javeriana.brands.entity;

import jakarta.persistence.*;

/**
 * Entidad que representa una marca.
 */
@Entity
@Table(name = "brands")
public class Brand {

    /**
     * Constructor por defecto de la clase Brand.
     */
    public Brand() {
    }

    /**
     * Constructor de la clase Brand con el nombre de la marca.
     *
     * @param name El nombre de la marca.
     */
    public Brand(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    /**
     * Obtiene el ID de la marca.
     *
     * @return El ID de la marca.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID de la marca.
     *
     * @param id El ID de la marca.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la marca.
     *
     * @return El nombre de la marca.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la marca.
     *
     * @param name El nombre de la marca.
     */
    public void setName(String name) {
        this.name = name;
    }
}
