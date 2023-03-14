package edu.javeriana.brands.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "brands")
public class Brand {

    public Brand() {
    }

    public Brand(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private  String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
