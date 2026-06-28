package com.ecommerce.product.service;

import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ecommerce.product.dto.ProductAddRequest;
import com.ecommerce.product.dto.ProductAddResponse;
import com.ecommerce.product.dto.ProductUpdateRequest;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repo;

	public ProductAddResponse addProduct(ProductAddRequest req) {
		ProductAddResponse response = new ProductAddResponse();
		Optional<Product> existingProduct = repo.findByName(req.getName());

		if (existingProduct.isPresent()) {
			response.setMessage("Product already exists with same name");

		} else {
			Product product = new Product();
			product.setName(req.getName());
			product.setDescription(req.getDescription());
			product.setPrice(req.getPrice());
			product.setQuantity(req.getQuantity());
			product.setCategory(req.getCategory());
			repo.save(product);
			response.setName(product.getName());
			response.setProductId(product.getId());
			response.setMessage("Product added successfully!");
		}

		return response;

	}
	
	public Product getProductById(Long id) {
	    return repo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Product not found"));
	}
	
	public List<Product> searchByNameOrCategory(String name,String category) {
		if (name != null && category != null) {
	        return repo.findByNameOrCategoryContainingIgnoreCase(
	                name, category);
	    }

	    if (name != null) {
	        return repo.findByNameContainingIgnoreCase(name);
	    }

	    if (category != null) {
	        return repo.findByCategoryContainingIgnoreCase(category);
	    }

	    return repo.findAll();
	}

	public String deleteProduct(Long id) {

		Optional<Product> existingProduct = repo.findById(id);

		if (existingProduct.isPresent()) {
			repo.deleteById(id);
			return "Product Id " + id + " is deleted Successfully";

		} else {
			return " Product with id " + id + " doesn't exist";
		}

	}

	public List<Product> showAllProducts() {
		return repo.findAll();

	}
	//pagination
	public Page<Product> getProducts(Pageable pageable) {
	    return repo.findAll(pageable);
	}
	

	public Product updateProduct(Long id, ProductUpdateRequest req) {

	    Product product = repo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Product not found"));

	    if (req.getName() != null) {
	        product.setName(req.getName());
	    }

	    if (req.getDescription() != null) {
	        product.setDescription(req.getDescription());
	    }

	    if (req.getPrice() != null) {
	        product.setPrice(req.getPrice());
	    }

	    if (req.getQuantity() != null) {
	        product.setQuantity(req.getQuantity());
	    }

	    return repo.save(product);
	}

}
