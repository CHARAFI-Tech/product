package com.carrefour.trial.product.mapper;

import com.carrefour.trial.product.dto.ProductData;
import com.carrefour.trial.product.entity.InventoryStatus;
import com.carrefour.trial.product.entity.Product;
import com.carrefour.trial.product.exception.InvalidInventoryStatusException;

public class ProductPopulator {

    private ProductPopulator() {

    }

    public static ProductData populateToProductData(Product source, ProductData target) {
        target.setId(source.getId());
        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setPrice(source.getPrice());
        target.setQuantity(source.getQuantity());
        target.setCategory(source.getCategory());
        target.setImage(source.getImage());
        target.setCategory(source.getCategory());
        target.setPrice(source.getPrice());
        target.setQuantity(source.getQuantity());
        target.setInternalReference(source.getInternalReference());
        target.setShellId(source.getShellId());
        target.setInventoryStatus(source.getInventoryStatus().name());
        target.setRating(source.getRating());
        target.setCreatedAt(source.getCreatedAt());
        target.setUpdatedAt(source.getUpdatedAt());
        return target;
    }

    public static Product populateToProduct(ProductData source, Product target) {
        target.setId(source.getId());
        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setPrice(source.getPrice());
        target.setQuantity(source.getQuantity());
        target.setCategory(source.getCategory());
        target.setImage(source.getImage());
        target.setCategory(source.getCategory());
        target.setPrice(source.getPrice());
        target.setQuantity(source.getQuantity());
        target.setInternalReference(source.getInternalReference());
        target.setShellId(source.getShellId());

        try {
            target.setInventoryStatus(InventoryStatus.valueOf(source.getInventoryStatus()));
        } catch (IllegalArgumentException e) {
            throw new InvalidInventoryStatusException("Invalid inventory status: " + source.getInventoryStatus());
        }

        target.setRating(source.getRating());
        target.setCreatedAt(source.getCreatedAt());
        target.setUpdatedAt(source.getUpdatedAt());
        return target;
    }
}
