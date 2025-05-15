package com.example.hotel_8.controller;

import com.example.hotel_8.entity.Room;
import com.example.hotel_8.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

  private final RoomService roomService;

  @GetMapping("/by-hotel/{hotelId}")
  public ResponseEntity<List<Room>> getRoomsByHotelId(@PathVariable Long hotelId) {
    return ResponseEntity.ok(roomService.getRoomsByHotelId(hotelId));
  }

  @PostMapping
  public ResponseEntity<Room> createRoom(@RequestBody Room room) {
    return ResponseEntity.ok(roomService.createRoom(room));
  }
}