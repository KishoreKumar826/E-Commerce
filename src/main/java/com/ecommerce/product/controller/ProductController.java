package com.ecommerce.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product.dto.ProductAddRequest;
import com.ecommerce.product.dto.ProductAddResponse;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/product")
public class ProductController {
	@Autowired
  public ProductService prodService;
	
	public List<Product> allProducts=new ArrayList<>();
	
	@PostMapping("/addProduct")
	public ProductAddResponse addProduct(@Valid @RequestBody ProductAddRequest ProdReq ) {
		
		return prodService.addProduct(ProdReq);
		
	}
	
	@GetMapping("/viewAllProducts")
	public List<Product> viewProducts(){
		return prodService.showAllProducts();
		
	}
	
	@DeleteMapping("deleteProduct/{id}")
	public String deleteProduct(@PathVariable Long id) {
		return prodService.deleteProduct(id);
		
	}
	
	

}
