package com.kirubha.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.kirubha.productservice.dto.ProductRequest;
import com.kirubha.productservice.dto.ProductResponse;
import com.kirubha.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController{
	
	private final ProductService productService;
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
		
		return productService.createProduct(productRequest);
		
	}
	
	@GetMapping("/getAllproduct")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProductResponse> getAllProduct() {
		return productService.getAllProduct();
	}
	
	
	
	
}