package com.on4june.rating_service.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.on4june.rating_service.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating, String>{
	
	List<Rating> findByUserId(String userId);
	
	List<Rating> findByHotelId(String hotelId);
}
