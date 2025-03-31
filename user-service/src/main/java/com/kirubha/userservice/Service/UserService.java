package com.kirubha.userservice.Service;


import org.springframework.stereotype.Service;

import com.kirubha.userservice.DTO.UserRequest;
import com.kirubha.userservice.DTO.UserResponse;
import com.kirubha.userservice.Entity.User;
import com.kirubha.userservice.exception.UserNotFoundException;
import com.kirubha.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService{
	
	private final UserRepository userRepository;
	
	public UserResponse createUser(UserRequest userRequest) {
        User user = User.builder()
        		.id(userRequest.id())
        		.name(userRequest.name())
        		.address(userRequest.address())
        		.phoneNumber(userRequest.phoneNumber())
        		.build();
        userRepository.save(user);	
        log.info("User Created Successfully");
        return new UserResponse(user.getId(),user.getName(),user.getAddress(),user.getPhoneNumber());
        		  
        		
	}

	public UserResponse getUserById(long id) {
		
		 return userRepository.findById(id)
		            .map(user -> new UserResponse(user.getId(), user.getName(), user.getAddress(), user.getPhoneNumber()))
		            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
		}
	
}