package com.carrefour.trial.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "Product", description = "Schema to hold Product information")
public class ProductData {

    @Schema(description = "Product ID managed by backend, can be provided by frontend for updates", example = "1")
    private Long id;

    @NotEmpty(message = "code can not be a null or empty")
    @Schema(description = "Product code", example = "H9837POHY")
    private String code;

    @Schema(description = "Name of the Product", example = "NUTELLA")
    @NotEmpty(message = "Name can not be null or empty")
    private String name;

    @Schema(description = "Description of the Product", example = "NUTELLA hazelnut and cocoa spread")
    @NotEmpty(message = "Description can not be null or empty")
    private String description;

    @Schema(description = "image url of the Product", example = "https://media.carrefour.fr/medias/b5b01bf885a440f7aec22c638c6f9ebe/p_1500x1500/03017620425035_H1L1_s20.jpeg")
    @NotEmpty(message = "image url can not be null or empty")
    private String image;

    @Schema(description = "The Product Category", example = "Spreads and Creams")
    @NotEmpty(message = "the product category can not be null or empty")
    private String category;

    @Schema(description = "The Product price", example = "6.59")
    @NotEmpty(message = "the product price can not be null or empty")
    private double price;

    @Schema(description = "Quantity of the Product", example = "116")
    @NotEmpty(message = "the product quantity can not be null or empty")
    private int quantity;

    @Schema(description = "Internal reference of the Product", example = "3017620425035")
    @NotEmpty(message = "the product Internal reference can not be null or empty")
    private String internalReference;

    @Schema(description = "shell Id of the Product", example = "10000")
    @NotEmpty(message = "the product shell Id can not be null or empty")
    private Long shellId;

    @Schema(description = "inventory status of the Product", example = "INSTOCK",  allowableValues =  {"INSTOCK", "LOWSTOCK", "OUTOFSTOCK"})
    @NotEmpty(message = "the product inventory Status can not be null or empty")
    private String inventoryStatus;

    @Schema(description = "inventory status of the Product", example = "4.63")
    @NotEmpty(message = "the product inventory Status can not be null or empty")
    private double rating;

    @Schema(description = "Date and time when the product was created", example = "2023-10-07T12:30:00")
    @JsonIgnore
    private LocalDateTime createdAt;

    @Schema(description = "Date and time when the product was last updated", example = "2023-10-07T12:30:00")
    @JsonIgnore
    private LocalDateTime updatedAt;
}
