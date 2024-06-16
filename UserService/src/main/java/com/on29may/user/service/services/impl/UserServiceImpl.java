package com.on29may.user.service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.on29may.user.service.entities.Hotel;
import com.on29may.user.service.entities.Rating;
import com.on29may.user.service.entities.User;
import com.on29may.user.service.exceptions.ResourceNotFoundException;
import com.on29may.user.service.externals.services.HotelService;
import com.on29may.user.service.repositories.UserRepository;
import com.on29may.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	
	@Override
	public User saveUser(User user) {
		String uid = UUID.randomUUID().toString();
		user.setUserId(uid);
		return repository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		
		List<User> allUsers = repository.findAll();
		
//		for (User user : allUsers) {
//			ArrayList<Rating> ratings = restTemplate.getForObject("http://127.0.0.1:8083/ratings/users/"+user.getUserId(), ArrayList.class);
//			user.setRatings(ratings);
//		}
		
		return allUsers;
	}

	@Override
	public User getUser(String userId) {
		User user = repository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Given user is not found on the server !!"));
		
		Rating[] ratingArray = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		List<Rating> ratings = Arrays.stream(ratingArray).toList();
		
		List<Rating> ratingsWithHotel = ratings.stream().map(rating->{
//			ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//			Hotel hotel = hotelEntity.getBody();
			// WE CAN EITHER USE FEIGN CLIENT OR REST TEMPLATE
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratingsWithHotel);
		
		return user;
	}

}
