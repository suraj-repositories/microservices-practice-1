package com.on4june.hotel_service.service;

import java.util.List;

import com.on4june.hotel_service.entities.Hotel;

public interface HotelService {

	Hotel saveHotel(Hotel hotel);
	
	List<Hotel> findAll();
	
	Hotel getHotel(String id);
}
