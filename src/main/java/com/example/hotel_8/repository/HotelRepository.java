package com.example.hotel_8.repository;

import com.example.hotel_8.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
