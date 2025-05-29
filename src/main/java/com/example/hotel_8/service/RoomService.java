package com.example.hotel_8.service;

import com.example.hotel_8.entity.Hotel;
import com.example.hotel_8.entity.Room;
import com.example.hotel_8.repository.HotelRepository;
import com.example.hotel_8.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

  private final RoomRepository roomRepository;
  private final HotelRepository hotelRepository;

  public List<Room> getRoomsByHotelId(Long hotelId) {
    return roomRepository.findByHotelId(hotelId);
  }

  public Room getRoomById(Long id) {
    return roomRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
  }

  public Room createRoom(Room room) {
    return roomRepository.save(room);
  }

  @Transactional
  public Room addRoomToHotel(Long hotelId, Room room) {
    Hotel hotel = hotelRepository.findById(hotelId)
        .orElseThrow(() -> new RuntimeException("Hotel not found"));
    room.setHotel(hotel);
    return roomRepository.save(room);
  }
}