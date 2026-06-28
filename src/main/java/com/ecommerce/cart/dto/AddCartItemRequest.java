package com.ecommerce.cart.dto;

import com.ecommerce.product.entity.Product;

public class AddCartItemRequest {
	
	private Integer quantity;
	 private Long productId;
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long id) {
		this.productId = id;
	}
	

}
