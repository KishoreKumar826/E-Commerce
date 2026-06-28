package com.ecommerce.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.cart.dto.AddCartItemRequest;
import com.ecommerce.cart.dto.AddCartItemResponse;
import com.ecommerce.cart.dto.ViewCartResponse;
import com.ecommerce.cart.service.CartService;


@RestController
@RequestMapping("/api/cart")
public class CartController {
	@Autowired
	private CartService cartserv;
	
	@PostMapping("/add")
	public AddCartItemResponse addCart(@RequestBody AddCartItemRequest cart) {
		
		return cartserv.addCart(cart);
	}
	
	@GetMapping("/view")
	public ViewCartResponse viewCart() {
		return cartserv.viewCart();
	}
	

}
