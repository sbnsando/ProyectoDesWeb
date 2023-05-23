package edu.javeriana.brands.service;

import edu.javeriana.brands.entity.Brand;
import edu.javeriana.brands.repository.BrandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandService brandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Arrange
        List<Brand> expectedBrands = new ArrayList<>();
        expectedBrands.add(new Brand("Brand 1"));
        expectedBrands.add(new Brand("Brand 2"));

        Mockito.when(brandRepository.findAll()).thenReturn(expectedBrands);

        // Act
        List<Brand> actualBrands = brandService.list();

        // Assert
        Assertions.assertEquals(expectedBrands, actualBrands);
        Mockito.verify(brandRepository).findAll();
    }

    @Test
    void testGetOneById() {
        // Arrange
        int brandId = 1;
        Brand expectedBrand = new Brand("Brand 1");

        Mockito.when(brandRepository.findById(brandId)).thenReturn(Optional.of(expectedBrand));

        // Act
        Brand actualBrand = brandService.getOneById(brandId);

        // Assert
        Assertions.assertEquals(expectedBrand, actualBrand);
        Mockito.verify(brandRepository).findById(brandId);
    }

    @Test
    void testGetOneByIdNotFound() {
        // Arrange
        int brandId = 1;

        Mockito.when(brandRepository.findById(brandId)).thenReturn(Optional.empty());

        // Act
        Brand actualBrand = brandService.getOneById(brandId);

        // Assert
        Assertions.assertNull(actualBrand);
        Mockito.verify(brandRepository).findById(brandId);
    }

    @Test
    void testGetOneByName() {
        // Arrange
        String brandName = "Brand 1";
        Brand expectedBrand = new Brand(brandName);

        Mockito.when(brandRepository.findByName(brandName)).thenReturn(expectedBrand);

        // Act
        Brand actualBrand = brandService.getOneByName(brandName);

        // Assert
        Assertions.assertEquals(expectedBrand, actualBrand);
        Mockito.verify(brandRepository).findByName(brandName);
    }

    @Test
    void testGetOneByNameNotFound() {
        // Arrange
        String brandName = "Brand 1";

        Mockito.when(brandRepository.findByName(brandName)).thenReturn(null);

        // Act
        Brand actualBrand = brandService.getOneByName(brandName);

        // Assert
        Assertions.assertNull(actualBrand);
        Mockito.verify(brandRepository).findByName(brandName);
    }

    @Test
    void testSave() {
        // Arrange
        Brand brand = new Brand("Brand 1");

        // Act
        brandService.save(brand);

        // Assert
        Mockito.verify(brandRepository).save(brand);
    }

    @Test
    void testDelete() {
        // Arrange
        int brandId = 1;

        // Act
        brandService.delete(brandId);

        // Assert
        Mockito.verify(brandRepository).deleteById(brandId);
    }

    @Test
    void testExistById() {
        // Arrange
        int brandId = 1;

        Mockito.when(brandRepository.existsById(brandId)).thenReturn(true);

        // Act
        boolean exists = brandService.existById(brandId);

        // Assert
        Assertions.assertTrue(exists);
        Mockito.verify(brandRepository).existsById(brandId);
    }

    @Test
    void testNotExistById() {
        // Arrange
        int brandId = 1;

        Mockito.when(brandRepository.existsById(brandId)).thenReturn(false);

        // Act
        boolean exists = brandService.existById(brandId);

        // Assert
        Assertions.assertFalse(exists);
        Mockito.verify(brandRepository).existsById(brandId);
    }

    @Test
    void testExistByName() {
        // Arrange
        String brandName = "Brand 1";

        Mockito.when(brandRepository.findByName(brandName)).thenReturn(new Brand(brandName));

        // Act
        boolean exists = brandService.existByName(brandName);

        // Assert
        Assertions.assertTrue(exists);
        Mockito.verify(brandRepository).findByName(brandName);
    }

    @Test
    void testNotExistByName() {
        // Arrange
        String brandName = "Brand 1";

        Mockito.when(brandRepository.findByName(brandName)).thenReturn(null);

        // Act
        boolean exists = brandService.existByName(brandName);

        // Assert
        Assertions.assertFalse(exists);
        Mockito.verify(brandRepository).findByName(brandName);
    }
}
