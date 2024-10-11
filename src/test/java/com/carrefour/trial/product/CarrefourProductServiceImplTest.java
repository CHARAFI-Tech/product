package com.carrefour.trial.product;

import com.carrefour.trial.product.constants.ProductConstants;
import com.carrefour.trial.product.dto.ProductData;
import com.carrefour.trial.product.entity.InventoryStatus;
import com.carrefour.trial.product.entity.Product;
import com.carrefour.trial.product.exception.ProductAlreadyExistsException;
import com.carrefour.trial.product.exception.ResourceNotFoundException;
import com.carrefour.trial.product.mapper.ProductPopulator;
import com.carrefour.trial.product.repository.CarrefourProductRepository;
import com.carrefour.trial.product.service.impl.CarrefourProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarrefourProductServiceImplTest {

    @Mock
    private CarrefourProductRepository carrefourProductRepository;

    @InjectMocks
    private CarrefourProductServiceImpl carrefourProductService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void fetchAllProducts_shouldReturnListOfProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        product1.setInventoryStatus(InventoryStatus.INSTOCK);
        product2.setInventoryStatus(InventoryStatus.OUTOFSTOCK);
        when(carrefourProductRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
        List<ProductData> result = carrefourProductService.fetchAllProducts();
        assertEquals(2, result.size());
        verify(carrefourProductRepository).findAll();
    }

/*    @Test
    void fetchProduct_shouldReturnProductData_whenProductExists() {
        Product product = new Product();
        ProductData productData = new ProductData();
        when(carrefourProductRepository.findById(1L)).thenReturn(Optional.of(product));
        when(ProductPopulator.populateToProductData(product, new ProductData())).thenReturn(productData);
        ProductData result = carrefourProductService.fetchProduct(1L);
        assertNotNull(result);
        verify(carrefourProductRepository).findById(1L);
    }*/

    @Test
    void fetchProduct_shouldThrowResourceNotFoundException_whenProductDoesNotExist() {
        when(carrefourProductRepository.findById(1L)).thenReturn(Optional.empty());
        Executable executable = () -> carrefourProductService.fetchProduct(1L);
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, executable);
        String expectedMessage = String.format("%s not found with the given input data %s : '%s'", ProductConstants.PRODUCT, ProductConstants.ID, 1L);
        assertEquals(expectedMessage, exception.getMessage());
        verify(carrefourProductRepository).findById(1L);
    }


/*    @Test
    void createProduct_shouldReturnProductData_whenProductDoesNotExist() {
        ProductData productData = new ProductData();
        Product product = new Product();
        when(carrefourProductRepository.findByCode(productData.getCode())).thenReturn(Optional.empty());
        when(ProductPopulator.populateToProduct(productData, new Product())).thenReturn(product);
        when(carrefourProductRepository.save(product)).thenReturn(product);
        when(ProductPopulator.populateToProductData(product, productData)).thenReturn(productData);

        ProductData result = carrefourProductService.createProduct(productData);

        assertNotNull(result);
        verify(carrefourProductRepository).findByCode(productData.getCode());
        verify(carrefourProductRepository).save(product);
    }*/

    @Test
    void createProduct_shouldThrowProductAlreadyExistsException_whenProductExists() {
        ProductData productData = new ProductData();
        Product existingProduct = new Product();
        when(carrefourProductRepository.findByCode(productData.getCode())).thenReturn(Optional.of(existingProduct));

        Executable executable = () -> carrefourProductService.createProduct(productData);

        ProductAlreadyExistsException exception = assertThrows(ProductAlreadyExistsException.class, executable);
        assertEquals("Product already registred with given code : " + productData.getCode(), exception.getMessage());
        verify(carrefourProductRepository).findByCode(productData.getCode());
    }

/*    @Test
    void updateProduct_shouldReturnTrue_whenProductExists() {
        ProductData productData = new ProductData();
        Product existingProduct = new Product();
        when(carrefourProductRepository.findById(productData.getId())).thenReturn(Optional.of(existingProduct));
        when(ProductPopulator.populateToProduct(productData, existingProduct)).thenReturn(existingProduct);
        boolean result = carrefourProductService.updateProduct(productData);
        assertTrue(result);
        verify(carrefourProductRepository).findById(productData.getId());
        verify(carrefourProductRepository).save(existingProduct);
    }*/

    @Test
    void updateProduct_shouldThrowResourceNotFoundException_whenProductDoesNotExist() {
        ProductData productData = new ProductData();
        productData.setId(1L);
        when(carrefourProductRepository.findById(productData.getId())).thenReturn(Optional.empty());
        Executable executable = () -> carrefourProductService.updateProduct(productData);
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, executable);
        String expectedMessage = String.format("%s not found with the given input data %s : '%s'", ProductConstants.PRODUCT, ProductConstants.ID, productData.getId());
        assertEquals(expectedMessage, exception.getMessage());
        verify(carrefourProductRepository).findById(productData.getId());
    }


    @Test
    void deleteProduct_shouldReturnTrue_whenProductExists() {
        when(carrefourProductRepository.existsById(1L)).thenReturn(true);
        boolean result = carrefourProductService.deleteProduct(1L);
        assertTrue(result);
        verify(carrefourProductRepository).deleteById(1L);
    }

    @Test
    void deleteProduct_shouldThrowResourceNotFoundException_whenProductDoesNotExist() {
        when(carrefourProductRepository.existsById(1L)).thenReturn(false);
        Executable executable = () -> carrefourProductService.deleteProduct(1L);
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, executable);
        String expectedMessage = String.format("%s not found with the given input data %s : '%s'", ProductConstants.PRODUCT, ProductConstants.ID, 1L);
        assertEquals(expectedMessage, exception.getMessage());
        verify(carrefourProductRepository).existsById(1L);
    }

}

