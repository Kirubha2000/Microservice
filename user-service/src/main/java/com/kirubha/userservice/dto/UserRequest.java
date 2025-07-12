package com.kirubha.userservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = false)
public record UserRequest(
		@NotBlank(message = "Name must not be blank") 
		@Size(max = 18, message = "Name must not exceed 18 characters") 
		String name,
		@Size(max = 50, message = "Address must not exceed 50 characters") 
		String address, 
		String dateOfBirth,
		@Size(max = 10, message = "Phonenumber must be 10 digit") 
		String phoneNumber, 
		@Email(message = "Invalid Email") 
		String email,
		@Size(max=1,message = "Please enter 'M' for Male or 'F' for Female")
		String gender) {

}