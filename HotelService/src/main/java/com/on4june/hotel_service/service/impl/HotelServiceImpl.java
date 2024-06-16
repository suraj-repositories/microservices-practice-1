package com.on4june.hotel_service.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.on4june.hotel_service.entities.Hotel;
import com.on4june.hotel_service.exceptions.ResourceNotFoundException;
import com.on4june.hotel_service.repositories.HotelRepository;
import com.on4june.hotel_service.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public Hotel saveHotel(Hotel hotel) {
		String string = UUID.randomUUID().toString();
		hotel.setId(string);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> findAll() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotel(String id) {
		return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The given hotel is not found !!"));
	}

}
