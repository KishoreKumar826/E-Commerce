package com.ecommerce.cart.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ecommerce.auth.entity.User;
import com.ecommerce.auth.repository.UserRepository;
import com.ecommerce.cart.dto.AddCartItemRequest;
import com.ecommerce.cart.dto.AddCartItemResponse;
import com.ecommerce.cart.dto.ViewCartResponse;
import com.ecommerce.cart.entity.Cart;
import com.ecommerce.cart.entity.CartItem;
import com.ecommerce.cart.repository.CartItemRepository;
import com.ecommerce.cart.repository.CartRepository;
import com.ecommerce.product.dto.ProductAddRequest;
import com.ecommerce.product.dto.ProductAddResponse;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.repository.ProductRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private CartItemRepository cartItemRepo;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private UserRepository userRepo;

	public AddCartItemResponse addCart(AddCartItemRequest req) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		Cart cart = cartRepo.findByUser(user).orElseGet(() -> {
			Cart newCart = new Cart();
			newCart.setUser(user);
			return cartRepo.save(newCart);
		});

		Product product = productRepo.findById(req.getProductId())
				.orElseThrow(() -> new RuntimeException("Product not found"));

		Optional<CartItem> existingItem = cartItemRepo.findByCartAndProduct(cart, product);
		CartItem item;
		AddCartItemResponse response = new AddCartItemResponse();

		if (existingItem.isPresent()) {

			item = existingItem.get();

			item.setQuantity(item.getQuantity() + req.getQuantity());

			

		} else {

			item = new CartItem();

			item.setCart(cart);
			item.setProduct(product);
			item.setQuantity(req.getQuantity());

			
		}
		cartItemRepo.save(item);

		response.setProductId(product.getId());
		response.setProductName(product.getName());
		response.setQuantity(item.getQuantity());
		response.setPrice(product.getPrice());
		response.setMessage("Product added to cart");

		return response;

	}

	public ViewCartResponse viewCart() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		Cart cart = cartRepo.findByUser(user).orElseThrow(() -> new RuntimeException("Cart not found"));

		List<CartItem> cartItems = cartItemRepo.findByCart(cart);

		List<AddCartItemResponse> items = new ArrayList<>();

		BigDecimal totalAmount = BigDecimal.ZERO;

		for (CartItem item : cartItems) {

			AddCartItemResponse response = new AddCartItemResponse();

			response.setProductId(item.getProduct().getId());

			response.setProductName(item.getProduct().getName());

			response.setPrice(item.getProduct().getPrice());

			response.setQuantity(item.getQuantity());

			totalAmount = totalAmount.add(
				    item.getProduct()
				        .getPrice()
				        .multiply(BigDecimal.valueOf(item.getQuantity()))
				);

			items.add(response);
		}

		ViewCartResponse cartResponse = new ViewCartResponse();

		cartResponse.setItems(items);
		cartResponse.setTotalAmount(totalAmount);

		return cartResponse;
	}

}
