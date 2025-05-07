package com.kirubha.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kirubha.productservice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	boolean existsByName(String name);
	
}