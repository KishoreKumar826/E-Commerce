package com.ecommerce.auth.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.auth.dto.LoginRequest;
import com.ecommerce.auth.dto.LoginResponse;
import com.ecommerce.auth.dto.RegistrationRequest;
import com.ecommerce.auth.entity.User;
import com.ecommerce.auth.repository.UserRepository;
import com.ecommerce.auth.security.JwtService;

@Service
public class AuthService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	
	

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
	public LoginResponse login(LoginRequest request) {
	    LoginResponse loginResponse = new LoginResponse();

		User existingUser = userRepository
		        .findByEmail(request.getEmail())
		        .orElse(null);
		if (existingUser==null) {
			loginResponse.setMessage("Invalid email or password");
			return loginResponse;
		}

		if (!passwordEncoder.matches(request.getPassword(), existingUser.getPassword())) {
			loginResponse.setMessage("Invalid email or password");
			return loginResponse;
		}
		 String token =
		            jwtService.generateToken(existingUser.getEmail());

		loginResponse.setMessage("Welcome " + existingUser.getName());
		loginResponse.setRole(existingUser.getRole());
		loginResponse.setToken(token);
		

		return loginResponse;
		
	}
}
