package com.example.hotel_8.controller;

import com.example.hotel_8.dto.HotelCreateRequest;
import com.example.hotel_8.dto.RoomCreateRequest;
import com.example.hotel_8.entity.Hotel;
import com.example.hotel_8.entity.Room;
import com.example.hotel_8.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.hotel_8.service.RoomService;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

  private final HotelService hotelService;
  private final RoomService roomService;

  @GetMapping
  public ResponseEntity<List<Hotel>> getAllHotels() {
    return ResponseEntity.ok(hotelService.getAllHotels());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
    return ResponseEntity.ok(hotelService.getHotelById(id));
  }

  @PostMapping
  public ResponseEntity<Hotel> createHotel(@RequestBody HotelCreateRequest request) {
    Hotel hotel = new Hotel();
    hotel.setName(request.getName());
    hotel.setLocation(request.getLocation());
    hotel.setRating(request.getRating());
    return ResponseEntity.ok(hotelService.createHotel(hotel));
  }

  @PostMapping("/{hotelId}/rooms")
  public ResponseEntity<Room> addRoomToHotel(
      @PathVariable Long hotelId,
      @RequestBody RoomCreateRequest request) {
    Room room = new Room();
    room.setNumber(request.getNumber());
    room.setType(request.getType());
    room.setPricePerNight(request.getPricePerNight());
    return ResponseEntity.ok(roomService.addRoomToHotel(hotelId, room));
  }
}