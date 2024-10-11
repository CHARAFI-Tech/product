package com.carrefour.trial.product.service.impl;

import com.carrefour.trial.product.constants.ProductConstants;
import com.carrefour.trial.product.dto.ProductData;
import com.carrefour.trial.product.entity.Product;
import com.carrefour.trial.product.exception.ProductAlreadyExistsException;
import com.carrefour.trial.product.exception.ResourceNotFoundException;
import com.carrefour.trial.product.mapper.ProductPopulator;
import com.carrefour.trial.product.repository.CarrefourProductRepository;
import com.carrefour.trial.product.service.CarrefourProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarrefourProductServiceImpl implements CarrefourProductService {

    private CarrefourProductRepository carrefourProductRepository;

    @Override
    public List<ProductData> fetchAllProducts() {
        return carrefourProductRepository.findAll().stream()
                .map(product -> ProductPopulator.populateToProductData(product, new ProductData()))
                .toList();
    }

    @Override
    public ProductData fetchProduct(Long id) {
        return carrefourProductRepository.findById(id)
                .map(product -> ProductPopulator.populateToProductData(product, new ProductData()))
                .orElseThrow(() -> new ResourceNotFoundException(ProductConstants.PRODUCT, ProductConstants.ID, id));
    }

    @Override
    public ProductData createProduct(ProductData productData) {
        Optional<Product> productModel = carrefourProductRepository.findByCode(productData.getCode());
        if(productModel.isPresent()) {
            throw new ProductAlreadyExistsException("Product already registred with given code : " + productData.getCode());
        }
        Product product = ProductPopulator.populateToProduct(productData, new Product());
        Product savedProduct = carrefourProductRepository.save(product);
        return ProductPopulator.populateToProductData(savedProduct, productData);
    }

    @Override
    public boolean updateProduct(ProductData productData) {
        return carrefourProductRepository.findById(productData.getId()).map(existingProduct -> {
                    ProductPopulator.populateToProduct(productData, existingProduct);
                    carrefourProductRepository.save(existingProduct);
                    return true;
                }).orElseThrow(() -> new ResourceNotFoundException(ProductConstants.PRODUCT, ProductConstants.ID, productData.getId()));
    }

    @Override
    public boolean deleteProduct(Long id) {
        if(carrefourProductRepository.existsById(id)) {
            carrefourProductRepository.deleteById(id);
            return true;
        }else {
            throw new ResourceNotFoundException(ProductConstants.PRODUCT, ProductConstants.ID, id);
        }
    }
}
