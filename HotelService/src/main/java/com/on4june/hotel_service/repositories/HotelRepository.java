package com.on4june.hotel_service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.on4june.hotel_service.entities.Hotel;

public interface HotelRepository extends MongoRepository<Hotel, String> {

}
