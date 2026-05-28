package com.ecommerce.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.auth.dto.LoginRequest;
import com.ecommerce.auth.dto.LoginResponse;
import com.ecommerce.auth.dto.RegistrationRequest;
import com.ecommerce.auth.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class Authcontroller {

	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public String registerUser(@RequestBody RegistrationRequest request) {

		return authService.register(request);
	}
	
	@PostMapping("/login")
	public LoginResponse login(
	        @RequestBody LoginRequest request
	) {

	    return authService.login(request);
	}
	

}
