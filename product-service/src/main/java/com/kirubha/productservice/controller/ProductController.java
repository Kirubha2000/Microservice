package com.kirubha.productservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.kirubha.productservice.dto.ProductRequest;
import com.kirubha.productservice.dto.ProductResponse;
import com.kirubha.productservice.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProductResponse createProduct(@Valid @RequestBody ProductRequest productRequest) {

		return productService.createProduct(productRequest);

	}

	@GetMapping("/getAllproduct")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProductResponse> getAllProduct() {
		return productService.getAllProduct();
	}

	@GetMapping("/getProductById/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ProductResponse productById(@PathVariable Long id) {
		return productService.productById(id);
	}

	@PatchMapping("/products/{id}")
	public ProductResponse updateProductPartially(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
		return productService.patchUpdate(id, updates);
	}

	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long id) {
	    productService.deleteProductById(id);

	    Map<String, String> response = new HashMap<>();
	    response.put("status", "success");
	    response.put("message", "Product deleted successfully");

	    return ResponseEntity.ok(response);
	}

}