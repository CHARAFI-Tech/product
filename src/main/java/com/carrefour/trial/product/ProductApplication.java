package com.carrefour.trial.product;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Products microservice REST API Documentation",
				description = "Carrefour Products microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "charafi Saad",
						email = "saad.charafi@alten.com",
						url = "https://www.alten.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.alten.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "Carrefour Products microservice REST API Documentation",
				url = "https://www.localhost:8080/swagger-ui.html"
		)
)
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}
