package edu.javeriana.brands.controller;

import edu.javeriana.brands.service.BrandService;
import edu.javeriana.brands.entity.Brand;
import io.micrometer.common.util.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandControllerTest {

    @Mock
    private BrandService brandService;

    @InjectMocks
    private BrandController brandController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Arrange
        List<Brand> expectedBrands = new ArrayList<>();
        when(brandService.list()).thenReturn(expectedBrands);

        // Act
        ResponseEntity<List<Brand>> response = brandController.list();
        List<Brand> actualBrands = response.getBody();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedBrands, actualBrands);
        verify(brandService, times(1)).list();
    }

    @Test
    void testGetById_whenBrandExists() {
        // Arrange
        int id = 1;
        Brand expectedBrand = new Brand();
        when(brandService.existById(id)).thenReturn(true);
        when(brandService.getOneById(id)).thenReturn(expectedBrand);

        // Act
        ResponseEntity<Brand> response = brandController.getById(id);
        Brand actualBrand = response.getBody();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedBrand, actualBrand);
        verify(brandService, times(1)).existById(id);
        verify(brandService, times(1)).getOneById(id);
    }

    @Test
    void testGetById_whenBrandDoesNotExist() {
        // Arrange
        int id = 1;
        when(brandService.existById(id)).thenReturn(false);

        // Act
        ResponseEntity<Brand> response = brandController.getById(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(brandService, times(1)).existById(id);
        verify(brandService, never()).getOneById(id);
    }

    @Test
    void testGetByName_whenBrandExists() {
        // Arrange
        String name = "TestBrand";
        Brand expectedBrand = new Brand();
        when(brandService.existByName(name)).thenReturn(true);
        when(brandService.getOneByName(name)).thenReturn(expectedBrand);

        // Act
        ResponseEntity<Brand> response = brandController.getByName(name);
        Brand actualBrand = response.getBody();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedBrand, actualBrand);
        verify(brandService, times(1)).existByName(name);
        verify(brandService, times(1)).getOneByName(name);
    }

    @Test
    void testGetByName_whenBrandDoesNotExist() {
        // Arrange
        String name = "TestBrand";
        when(brandService.existByName(name)).thenReturn(false);

        // Act
        ResponseEntity<Brand> response = brandController.getByName(name);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(brandService, times(1)).existByName(name);
        verify(brandService, never()).getOneByName(name);
    }

    @Test
    void testCreate_whenBrandIsValid() {
        // Arrange
        Brand brand = new Brand("TestBrand");

        // Act
        ResponseEntity<?> response = brandController.create(brand);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(brandService, times(1)).existByName(brand.getName());
        verify(brandService, times(1)).save(any(Brand.class));
    }

    @Test
    void testCreate_whenBrandNameIsBlank() {
        // Arrange
        Brand brand = new Brand();

        // Act
        ResponseEntity<?> response = brandController.create(brand);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(brandService, never()).existByName(anyString());
        verify(brandService, never()).save(any(Brand.class));
    }

    @Test
    void testCreate_whenBrandAlreadyExists() {
        // Arrange
        Brand brand = new Brand("TestBrand");
        when(brandService.existByName(brand.getName())).thenReturn(true);

        // Act
        ResponseEntity<?> response = brandController.create(brand);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(brandService, times(1)).existByName(brand.getName());
        verify(brandService, never()).save(any(Brand.class));
    }

    @Test
    void testUpdate_whenBrandExistsAndIsValid() {
        // Arrange
        int id = 1;
        Brand brand = new Brand("TestBrand");
        Brand existingBrand = new Brand("ExistingBrand");
        existingBrand.setId(id);
        when(brandService.existById(id)).thenReturn(true);
        when(brandService.existByName(brand.getName())).thenReturn(false);
        when(brandService.getOneById(id)).thenReturn(existingBrand);

        // Act
        ResponseEntity<?> response = brandController.update(id, brand);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(brandService, times(1)).existById(id);
        verify(brandService, times(1)).existByName(brand.getName());
        verify(brandService, times(1)).getOneById(id);
        verify(brandService, times(1)).save(any(Brand.class));
    }

    @Test
    void testUpdate_whenBrandDoesNotExist() {
        // Arrange
        int id = 1;
        Brand brand = new Brand("TestBrand");
        when(brandService.existById(id)).thenReturn(false);

        // Act
        ResponseEntity<?> response = brandController.update(id, brand);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(brandService, times(1)).existById(id);
        verify(brandService, never()).existByName(anyString());
        verify(brandService, never()).getOneById(id);
        verify(brandService, never()).save(any(Brand.class));
    }

    @Test
    void testUpdate_whenBrandNameIsBlank() {
        // Arrange
        int id = 1;
        Brand brand = new Brand();
        when(brandService.existById(id)).thenReturn(true);

        // Act
        ResponseEntity<?> response = brandController.update(id, brand);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(brandService, times(1)).existById(id);
        verify(brandService, never()).existByName(anyString());
        verify(brandService, never()).getOneById(id);
        verify(brandService, never()).save(any(Brand.class));
    }

    @Test
    void testDelete_whenBrandExists() {
        // Arrange
        int id = 1;
        when(brandService.existById(id)).thenReturn(true);

        // Act
        ResponseEntity<?> response = brandController.delete(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(brandService, times(1)).existById(id);
        verify(brandService, times(1)).delete(id);
    }

    @Test
    void testDelete_whenBrandDoesNotExist() {
        // Arrange
        int id = 1;
        when(brandService.existById(id)).thenReturn(false);

        // Act
        ResponseEntity<?> response = brandController.delete(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(brandService, times(1)).existById(id);
        verify(brandService, never()).delete(id);
    }
}
