package com.ecommerce.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.auth.entity.User;
import com.ecommerce.cart.entity.Cart;


public interface CartRepository extends JpaRepository<Cart, Long> {
	Optional<Cart> findByUser(User user);

}
