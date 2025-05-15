package com.example.hotel_8.repository;

import com.example.hotel_8.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

  List<Booking> findByGuestName(String guestName);

  List<Booking> findByUserId(Long userId);
}