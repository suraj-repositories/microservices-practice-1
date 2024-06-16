package com.on29may.user.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.on29may.user.service.entities.User;
import com.on29may.user.service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	// create user 
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User savedUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	
	// find all users 
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> allUsers = userService.getAllUsers();
		return ResponseEntity.ok(allUsers);
	}
	
	// find one user
	@GetMapping("/{userId}")
	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//	@Retry(name = "ratingHotelRetryService", fallbackMethod = "ratingHotelFallback")   // the given fallback method is called after the max retries (mentioned in application.yml file)
	@RateLimiter(name = "userRateLimiter" , fallbackMethod = "ratingHotelFallback")    // the given fallback method is called after max number of request per second is crossed
	public ResponseEntity<User> getUser(@PathVariable("userId") String userId){
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
		User user = User.builder()
			.name("dummy")
			.email("dummy@gmail.com")
			.about("The user is created dummy because some service is down")
			.ratings(new ArrayList<>())
			.build();
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
}
