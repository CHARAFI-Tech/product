package com.carrefour.trial.product.exception;

public class InvalidInventoryStatusException extends RuntimeException {
    public InvalidInventoryStatusException(String message) {
        super(message);
    }
}
