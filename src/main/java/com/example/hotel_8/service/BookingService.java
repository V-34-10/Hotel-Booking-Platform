package com.example.hotel_8.service;

import com.example.hotel_8.entity.Booking;
import com.example.hotel_8.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingService {

  private static final Logger logger = LoggerFactory.getLogger(BookingService.class);
  private final BookingRepository bookingRepository;

  public Booking createBooking(Booking booking) {
    logger.info("Creating new booking for guest: {}", booking.getGuestName());
    logger.debug("Booking details - Room: {}, From: {}, To: {}",
        booking.getRoom().getNumber(),
        booking.getFromDate(),
        booking.getToDate());

    Booking savedBooking = bookingRepository.save(booking);
    logger.info("Created booking with id: {}", savedBooking.getId());
    return savedBooking;
  }

  public List<Booking> getBookingsByGuestName(String guestName) {
    logger.info("Fetching bookings for guest: {}", guestName);
    List<Booking> bookings = bookingRepository.findByGuestName(guestName);
    logger.debug("Found {} bookings for guest: {}", bookings.size(), guestName);
    return bookings;
  }

  public List<Booking> getBookingsByUserId(Long userId) {
    logger.info("Fetching bookings for user id: {}", userId);
    List<Booking> bookings = bookingRepository.findByUserId(userId);
    logger.debug("Found {} bookings for user id: {}", bookings.size(), userId);
    return bookings;
  }
}