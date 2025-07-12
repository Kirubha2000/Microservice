package com.kirubha.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kirubha.userservice.dto.UserRequest;
import com.kirubha.userservice.dto.UserResponse;
import com.kirubha.userservice.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserServiceController{
	
	private final UserService userService;
	
	@PostMapping("/createUser")
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest){
		UserResponse userResponse = userService.createUser(userRequest);
		return ResponseEntity.status(201).body(userResponse);
	}
	
}