package com.ecommerce.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.product.entity.Product;


public interface ProductRepository extends JpaRepository<Product,Long> {
	Optional<Product> findByName(String name);
	Optional<Product> findById(Long id);
	List<Product> findByNameOrCategoryContainingIgnoreCase(String name,String Category);
	List<Product> findByNameContainingIgnoreCase(String name);
	List<Product> findByCategoryContainingIgnoreCase(String Category);
	

	boolean existsByName(String name);
	boolean existsById(Long id);

}
