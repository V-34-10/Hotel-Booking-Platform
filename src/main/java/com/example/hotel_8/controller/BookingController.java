package com.example.hotel_8.controller;

import com.example.hotel_8.dto.BookingResponseDTO;
import com.example.hotel_8.dto.CreateBookingRequest;
import com.example.hotel_8.entity.Booking;
import com.example.hotel_8.entity.Room;
import com.example.hotel_8.entity.User;
import com.example.hotel_8.service.BookingService;
import com.example.hotel_8.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

  private final BookingService bookingService;
  private final RoomService roomService;

  @PostMapping
  public ResponseEntity<Booking> createBooking(
      @RequestBody CreateBookingRequest request,
      @AuthenticationPrincipal UserDetails userDetails) {
    Room room = roomService.getRoomById(request.getRoomId());
    User user = (User) userDetails;

    Booking booking = new Booking();
    booking.setRoom(room);
    booking.setGuestName(request.getGuestName());
    booking.setFromDate(request.getFromDate());
    booking.setToDate(request.getToDate());
    booking.setUser(user);

    return ResponseEntity.ok(bookingService.createBooking(booking));
  }

  @GetMapping("/by-guest")
  public ResponseEntity<List<BookingResponseDTO>> getBookingsByGuest(
      @RequestParam String guestName) {
    List<Booking> bookings = bookingService.getBookingsByGuestName(guestName);
    List<BookingResponseDTO> responseDTOs = bookings.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
    return ResponseEntity.ok(responseDTOs);
  }

  @GetMapping("/my-bookings")
  public ResponseEntity<List<BookingResponseDTO>> getMyBookings(
      @AuthenticationPrincipal UserDetails userDetails) {
    User user = (User) userDetails;
    List<Booking> bookings = bookingService.getBookingsByUserId(user.getId());
    List<BookingResponseDTO> responseDTOs = bookings.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
    return ResponseEntity.ok(responseDTOs);
  }

  private BookingResponseDTO convertToDTO(Booking booking) {
    BookingResponseDTO dto = new BookingResponseDTO();
    dto.setId(booking.getId());
    dto.setHotelName(booking.getRoom().getHotel().getName());
    dto.setRoomNumber(booking.getRoom().getNumber());
    dto.setGuestName(booking.getGuestName());
    dto.setFromDate(booking.getFromDate());
    dto.setToDate(booking.getToDate());
    return dto;
  }
}