package com.kirubha.userservice.service;

import org.springframework.stereotype.Service;

import com.kirubha.userservice.dto.UserRequest;
import com.kirubha.userservice.dto.UserResponse;
import com.kirubha.userservice.entity.User;
import com.kirubha.userservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public UserResponse createUser(UserRequest userRequest) {

		User user = User.builder().name(userRequest.name()).address(userRequest.address())
				.dateOfBirth(userRequest.dateOfBirth()).phoneNumber(userRequest.phoneNumber())
				.email(userRequest.email()).gender(userRequest.gender()).build();
		User savedUser = userRepository.save(user);
		return new UserResponse(savedUser.getName());
	}

}
