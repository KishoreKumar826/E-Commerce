package com.ecommerce.auth.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.auth.dto.RegistrationRequest;
import com.ecommerce.auth.entity.User;
import com.ecommerce.auth.repository.UserRepository;

@Service
public class AuthService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public String register(RegistrationRequest request) {
		if (userRepository.existsByEmail(request.getEmail())) {
			return "Email already in use";
		}

		User user = new User();

		user.setName(request.getName());
		user.setEmail(request.getEmail());

		// encode password here
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		user.setRole("CUSTOMER");
		user.setCreatedAt(LocalDateTime.now());

		userRepository.save(user);

		return "User Registered Successfully";

	}
}
