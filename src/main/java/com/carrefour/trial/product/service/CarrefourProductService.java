package com.carrefour.trial.product.service;

import com.carrefour.trial.product.dto.ProductData;
import com.carrefour.trial.product.entity.Product;

import java.util.List;
import java.util.Optional;

public interface CarrefourProductService {

    /**
     * Fetch all products
     * @return All products
     */
    List<ProductData> fetchAllProducts();

    /**
     * fetch account by id
     * @param id - id of the product
     * @return Product details based on a given id
     */
    ProductData fetchProduct(Long id);

    /**
     * create product
     * @param productData - {@link ProductData}
     * @return ProductDto
     */
    ProductData createProduct(ProductData productData);

    /**
     * update product
     * @param productData - {@link ProductData}
     * @return boolean indicating if the update of Product detail is successful or not
     */
    boolean updateProduct(ProductData productData);

    /**
     * delete product
     * @param id - Input id
     * @return boolean indicating if the delete of product detail is successful or not
     */
    boolean deleteProduct(Long id);
}
