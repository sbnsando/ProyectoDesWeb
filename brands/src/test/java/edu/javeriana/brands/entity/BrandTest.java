package edu.javeriana.brands.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BrandTest {

    @Test
    void testDefaultConstructor() {
        Brand brand = new Brand();
        assertEquals(0, brand.getId());
        assertEquals(null, brand.getName());
    }

    @Test
    void testConstructorWithName() {
        String name = "Brand 1";
        Brand brand = new Brand(name);
        assertEquals(0, brand.getId());
        assertEquals(name, brand.getName());
    }

    @Test
    void testGetId() {
        Brand brand = new Brand();
        brand.setId(1);
        assertEquals(1, brand.getId());
    }

    @Test
    void testGetName() {
        String name = "Brand 1";
        Brand brand = new Brand();
        brand.setName(name);
        assertEquals(name, brand.getName());
    }

    @Test
    void testSetId() {
        int id = 1;
        Brand brand = new Brand();
        brand.setId(id);
        assertEquals(id, brand.getId());
    }

    @Test
    void testSetName() {
        String name = "Brand 1";
        Brand brand = new Brand();
        brand.setName(name);
        assertEquals(name, brand.getName());
    }
}
