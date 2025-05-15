package com.example.hotel_8.service;

import com.example.hotel_8.entity.Hotel;
import com.example.hotel_8.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HotelService {

  private static final Logger logger = LoggerFactory.getLogger(HotelService.class);
  private final HotelRepository hotelRepository;

  public List<Hotel> getAllHotels() {
    logger.info("Fetching all hotels");
    List<Hotel> hotels = hotelRepository.findAll();
    logger.debug("Found {} hotels", hotels.size());
    return hotels;
  }

  public Hotel getHotelById(Long id) {
    logger.info("Fetching hotel with id: {}", id);
    Hotel hotel = hotelRepository.findById(id)
        .orElseThrow(() -> {
          logger.error("Hotel not found with id: {}", id);
          return new RuntimeException("Hotel not found with id: " + id);
        });
    logger.debug("Found hotel: {}", hotel.getName());
    return hotel;
  }

  public Hotel createHotel(Hotel hotel) {
    logger.info("Creating new hotel: {}", hotel.getName());
    Hotel savedHotel = hotelRepository.save(hotel);
    logger.debug("Created hotel with id: {}", savedHotel.getId());
    return savedHotel;
  }
}