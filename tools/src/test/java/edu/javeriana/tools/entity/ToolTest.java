package edu.javeriana.tools.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToolTest {

    @Test
    void setId_ShouldSetIdCorrectly() {
        // Arrange
        int id = 1;
        Tool tool = new Tool();

        // Act
        tool.setId(id);

        // Assert
        assertEquals(id, tool.getId());
    }

    @Test
    void setCountry_ShouldSetCountryCorrectly() {
        // Arrange
        String country = "Country";
        Tool tool = new Tool();

        // Act
        tool.setCountry(country);

        // Assert
        assertEquals(country, tool.getCountry());
    }

    @Test
    void setIdBrand_ShouldSetIdBrandCorrectly() {
        // Arrange
        int idBrand = 2;
        Tool tool = new Tool();

        // Act
        tool.setIdBrand(idBrand);

        // Assert
        assertEquals(idBrand, tool.getIdBrand());
    }

    @Test
    void setName_ShouldSetNameCorrectly() {
        // Arrange
        String name = "Tool Name";
        Tool tool = new Tool();

        // Act
        tool.setName(name);

        // Assert
        assertEquals(name, tool.getName());
    }

    @Test
    void setDescription_ShouldSetDescriptionCorrectly() {
        // Arrange
        String description = "Tool Description";
        Tool tool = new Tool();

        // Act
        tool.setDescription(description);

        // Assert
        assertEquals(description, tool.getDescription());
    }

    @Test
    void setPrice_ShouldSetPriceCorrectly() {
        // Arrange
        int price = 100;
        Tool tool = new Tool();

        // Act
        tool.setPrice(price);

        // Assert
        assertEquals(price, tool.getPrice());
    }

    @Test
    void setImg_ShouldSetImgCorrectly() {
        // Arrange
        String img = "tool.jpg";
        Tool tool = new Tool();

        // Act
        tool.setImg(img);

        // Assert
        assertEquals(img, tool.getImg());
    }

    @Test
    void setCities_ShouldSetCitiesCorrectly() {
        // Arrange
        String cities = "City 1, City 2";
        Tool tool = new Tool();

        // Act
        tool.setCities(cities);

        // Assert
        assertEquals(cities, tool.getCities());
    }

    @Test
    void setQuantity_ShouldSetQuantityCorrectly() {
        // Arrange
        int quantity = 10;
        Tool tool = new Tool();

        // Act
        tool.setQuantity(quantity);

        // Assert
        assertEquals(quantity, tool.getQuantity());
    }

    @Test
    void constructor_ShouldSetPropertiesCorrectly() {
        // Arrange
        int idBrand = 2;
        String name = "Tool Name";
        String description = "Tool Description";
        int price = 100;
        String img = "tool.jpg";
        String country = "Country";
        String cities = "City 1, City 2";
        int quantity = 10;

        // Act
        Tool tool = new Tool(idBrand, name, description, price, img, country, cities, quantity);

        // Assert
        assertEquals(idBrand, tool.getIdBrand());
        assertEquals(name, tool.getName());
        assertEquals(description, tool.getDescription());
        assertEquals(price, tool.getPrice());
        assertEquals(img, tool.getImg());
        assertEquals(country, tool.getCountry());
        assertEquals(cities, tool.getCities());
        assertEquals(quantity, tool.getQuantity());
    }
}
