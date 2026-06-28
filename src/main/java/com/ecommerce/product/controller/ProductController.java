package com.ecommerce.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product.dto.ProductAddRequest;
import com.ecommerce.product.dto.ProductAddResponse;
import com.ecommerce.product.dto.ProductUpdateRequest;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/product")
public class ProductController {
	@Autowired
	public ProductService prodService;

	public List<Product> allProducts = new ArrayList<>();

	@PostMapping("/addProduct")
	public ProductAddResponse addProduct(@Valid @RequestBody ProductAddRequest ProdReq) {

		return prodService.addProduct(ProdReq);

	}

	@GetMapping("/viewAllProducts")
	public List<Product> viewProducts() {
		return prodService.showAllProducts();

	}

	@GetMapping
	public ResponseEntity<Page<Product>> getProducts(Pageable pageable) {
		return ResponseEntity.ok(prodService.getProducts(pageable));
	}

	@GetMapping("/viewProductById/{id}")
	public Product viewProductById(@PathVariable Long id) {
		return prodService.getProductById(id);
	}

	@GetMapping("/search")
	public ResponseEntity<List<Product>> searchProducts(@RequestParam(required = false) String name,
			@RequestParam(required = false) String category) {

		return ResponseEntity.ok(prodService.searchByNameOrCategory(name, category));
	}

	@DeleteMapping("deleteProduct/{id}")
	public String deleteProduct(@PathVariable Long id) {
		return prodService.deleteProduct(id);

	}

	@PatchMapping("/updateProduct/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequest req) {

		return prodService.updateProduct(id, req);
	}

}
