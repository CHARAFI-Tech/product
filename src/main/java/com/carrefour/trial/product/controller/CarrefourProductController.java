package com.carrefour.trial.product.controller;

import com.carrefour.trial.product.constants.ProductConstants;
import com.carrefour.trial.product.dto.ErrorResponseDto;
import com.carrefour.trial.product.dto.ProductData;
import com.carrefour.trial.product.dto.ResponseDto;
import com.carrefour.trial.product.service.CarrefourProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name= "REST APIS for product in Carrefour", description = "REST APIs in Carrefour to CREATE, UPDATE, FETCH and DELETE Product")
@RestController
@RequestMapping(path = "/products", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class CarrefourProductController {

    private CarrefourProductService carrefourProductService;

    @Operation(summary = "Create Product REST API", description = "REST API to create new Product inside Carrefour")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping
    public ResponseEntity<ResponseDto> createProduct(@RequestBody ProductData productData){
        carrefourProductService.createProduct(productData);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(ProductConstants.STATUS_201, ProductConstants.STATUS_201));
    }

    @GetMapping
    public ResponseEntity<List<ProductData>> fetchAllProducts(){
        List<ProductData> products = carrefourProductService.fetchAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @Operation(summary = "Fetch Product Details REST API", description = "REST API to fetch Product details based on a id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @GetMapping("/{id}")
    public ResponseEntity<ProductData> fetchProductById(@PathVariable("id") Long id){
        ProductData productData = carrefourProductService.fetchProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(productData);
    }

    @Operation(summary = "Update Product Details REST API", description = "REST API to update Product details based on a id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PatchMapping
    public ResponseEntity<ResponseDto> updateProduct(@RequestBody ProductData productData){
        boolean isUpdated = carrefourProductService.updateProduct(productData);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(ProductConstants.STATUS_201, ProductConstants.STATUS_201));
        }else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(ProductConstants.STATUS_417, ProductConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(summary = "Delete Product REST API", description = "REST API to delete Product based on a id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteProduct(@RequestParam Long id){
        boolean isDeleted = carrefourProductService.deleteProduct(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(ProductConstants.STATUS_200, ProductConstants.MESSAGE_200));
        }else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(ProductConstants.STATUS_417, ProductConstants.MESSAGE_417_DELETE));
        }
    }
}
