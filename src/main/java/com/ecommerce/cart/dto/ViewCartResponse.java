package com.ecommerce.cart.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class ViewCartResponse {
	private List<AddCartItemResponse> items;
	private BigDecimal totalAmount;
	
	public List<AddCartItemResponse> getItems() {
		return items;
	}
	public void setItems(List<AddCartItemResponse> items) {
		this.items = items;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

}
