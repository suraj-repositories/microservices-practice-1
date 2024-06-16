package com.on4june.rating_service.service;

import java.util.List;

import com.on4june.rating_service.entities.Rating;

public interface RatingService {
	
	Rating createRating(Rating rating);
	
	List<Rating> getAllRatings();
	
	List<Rating> getRatingByUserId(String userId);
	
	List<Rating> getRatingByHotelId(String hotelId);
}
