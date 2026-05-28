package com.ecommerce.auth.dto;


public class LoginResponse {
	private String token;
	private String message;
	private String role;
	  // empty constructor
    public LoginResponse() {
    	
    }

   

	public LoginResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
