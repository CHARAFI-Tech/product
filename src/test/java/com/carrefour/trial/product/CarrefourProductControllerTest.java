package com.carrefour.trial.product;

import com.carrefour.trial.product.controller.CarrefourProductController;
import com.carrefour.trial.product.dto.ProductData;
import com.carrefour.trial.product.dto.ResponseDto;
import com.carrefour.trial.product.service.CarrefourProductService;
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

class CarrefourProductControllerTest {

    @Mock
    private CarrefourProductService carrefourProductService;

    @InjectMocks
    private CarrefourProductController carrefourProductController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        ProductData productData = new ProductData();
        ResponseEntity<ResponseDto> response = carrefourProductController.createProduct(productData);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(carrefourProductService).createProduct(productData);
    }

    @Test
    void testFetchAllProducts() {
        List<ProductData> productList = new ArrayList<>();
        when(carrefourProductService.fetchAllProducts()).thenReturn(productList);
        ResponseEntity<List<ProductData>> response = carrefourProductController.fetchAllProducts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
        verify(carrefourProductService).fetchAllProducts();
    }

    @Test
    void testFetchProductById() {
        Long productId = 1L;
        ProductData productData = new ProductData();
        when(carrefourProductService.fetchProduct(productId)).thenReturn(productData);
        ResponseEntity<ProductData> response = carrefourProductController.fetchProductById(productId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productData, response.getBody());
        verify(carrefourProductService).fetchProduct(productId);
    }

    @Test
    void testUpdateProduct_Success() {
        ProductData productData = new ProductData();
        when(carrefourProductService.updateProduct(productData)).thenReturn(true);
        ResponseEntity<ResponseDto> response = carrefourProductController.updateProduct(productData);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("201", response.getBody().getStatusCode());
        verify(carrefourProductService).updateProduct(productData);
    }

    @Test
    void testUpdateProduct_Failure() {
        ProductData productData = new ProductData();
        when(carrefourProductService.updateProduct(productData)).thenReturn(false);
        ResponseEntity<ResponseDto> response = carrefourProductController.updateProduct(productData);
        assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode());
        assertEquals("417", response.getBody().getStatusCode());
        verify(carrefourProductService).updateProduct(productData);
    }

    @Test
    void testDeleteProduct_Success() {
        Long productId = 1L;
        when(carrefourProductService.deleteProduct(productId)).thenReturn(true);
        ResponseEntity<ResponseDto> response = carrefourProductController.deleteProduct(productId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("200", response.getBody().getStatusCode());
        verify(carrefourProductService).deleteProduct(productId);
    }

    @Test
    void testDeleteProduct_Failure() {
        Long productId = 1L;
        when(carrefourProductService.deleteProduct(productId)).thenReturn(false);
        ResponseEntity<ResponseDto> response = carrefourProductController.deleteProduct(productId);
        assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode());
        assertEquals("417", response.getBody().getStatusCode());
        verify(carrefourProductService).deleteProduct(productId);
    }
}
