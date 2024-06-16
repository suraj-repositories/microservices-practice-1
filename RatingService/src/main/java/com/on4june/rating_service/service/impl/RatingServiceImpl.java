package com.on4june.rating_service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.on4june.rating_service.entities.Rating;
import com.on4june.rating_service.repositories.RatingRepository;
import com.on4june.rating_service.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService{
	
	@Autowired
	private RatingRepository repository;

	@Override
	public Rating createRating(Rating rating) {
		return repository.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() {
		return repository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		return repository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return repository.findByHotelId(hotelId);
	}

}
