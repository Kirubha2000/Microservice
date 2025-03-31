package com.kirubha.userservice.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kirubha.userservice.DTO.UserRequest;
import com.kirubha.userservice.DTO.UserResponse;
import com.kirubha.userservice.Service.UserService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	 @PostMapping("/createUser")
	    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
		 
		 userService.createUser(userRequest);
		return new ResponseEntity<>("User Created Successfully",HttpStatus.CREATED) ;
		 
}
	 
	 @GetMapping("/getUserbyId/{id}") 
	 public ResponseEntity<UserResponse> getUserById(@PathVariable long id){
		 
		 return ResponseEntity.ok(userService.getUserById(id));
		 
	 }
}