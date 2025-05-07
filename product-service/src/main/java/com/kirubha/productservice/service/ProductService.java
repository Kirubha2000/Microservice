package com.kirubha.productservice.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.kirubha.productservice.exception.ProductAlreadyExistsException;
import com.kirubha.productservice.dto.ProductRequest;
import com.kirubha.productservice.dto.ProductResponse;
import com.kirubha.productservice.entity.Product;
import com.kirubha.productservice.exception.ProductNotCreatedException;
import com.kirubha.productservice.exception.ProductNotFoundException;
import com.kirubha.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

	private final ProductRepository productRepository;

	public ProductResponse createProduct(ProductRequest productRequest) {
		if (productRepository.existsByName(productRequest.name())) {
	        throw new ProductAlreadyExistsException("Product with the same name already exists.");
	    }
		try {
			Product product = Product.builder().id(productRequest.id()).name(productRequest.name())
					.description(productRequest.description()).price(productRequest.price()).build();
			Product savedProduct = productRepository.save(product);
			log.info("Product Created Successfully");

			return new ProductResponse(savedProduct.getId(), savedProduct.getName(), savedProduct.getDescription(),
					savedProduct.getPrice());
		} catch (DataAccessException ex) {
			log.error("Database error while creating product: {}", ex.getMessage());
			throw new ProductNotCreatedException("Failed to create product due to DB error.");
		}
	}

	public ProductResponse productById(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found"));

		return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
	}

	public List<ProductResponse> getAllProduct() {
		return productRepository.findAll().stream().map(product -> new ProductResponse(product.getId(),
				product.getName(), product.getDescription(), product.getPrice())).toList();
	}

	public ProductResponse patchUpdate(Long id, Map<String, Object> updates) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found"));

		updates.forEach((key, value) -> {
			switch (key) {
			case "name":
				product.setName((String) value);
				break;
			case "price":
				if (value instanceof Number) {
					product.setPrice(BigDecimal.valueOf(((Number) value).doubleValue()));
				}
				break;
			case "description":
				product.setDescription((String) value);
				break;
			default:
				log.warn("Unknown field '{}' provided in patch update", key);
			}
		});

		Product update = productRepository.save(product);
		return new ProductResponse(update.getId(), update.getName(), update.getDescription(), update.getPrice());
	}

	public void deleteProductById(Long id) {
		if (!productRepository.existsById(id)) {
			throw new ProductNotFoundException("Product not found for deletion");
		}
		productRepository.deleteById(id);
	}

}