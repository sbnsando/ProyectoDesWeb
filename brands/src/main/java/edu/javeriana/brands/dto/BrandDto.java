package edu.javeriana.brands.dto;

import java.util.List;

public class BrandDto {

    private  String name;

    public BrandDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
