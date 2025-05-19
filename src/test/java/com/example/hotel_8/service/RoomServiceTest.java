package com.example.hotel_8.service;

import com.example.hotel_8.entity.Room;
import com.example.hotel_8.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

  @Mock
  private RoomRepository roomRepository;

  @InjectMocks
  private RoomService roomService;

  private Room testRoom;

  @BeforeEach
  void setUp() {
    testRoom = new Room();
    testRoom.setId(1L);
    testRoom.setNumber("101");
  }

  @Test
  void getRoomsByHotelId_ShouldReturnListOfRooms() {
    List<Room> expectedRooms = Arrays.asList(testRoom);
    when(roomRepository.findByHotelId(1L)).thenReturn(expectedRooms);

    List<Room> actualRooms = roomService.getRoomsByHotelId(1L);

    assertNotNull(actualRooms);
    assertEquals(expectedRooms.size(), actualRooms.size());
    assertEquals(expectedRooms.get(0).getNumber(), actualRooms.get(0).getNumber());
    verify(roomRepository, times(1)).findByHotelId(1L);
  }

  @Test
  void getRoomById_WhenRoomExists_ShouldReturnRoom() {
    when(roomRepository.findById(1L)).thenReturn(Optional.of(testRoom));

    Room actualRoom = roomService.getRoomById(1L);

    assertNotNull(actualRoom);
    assertEquals(testRoom.getId(), actualRoom.getId());
    assertEquals(testRoom.getNumber(), actualRoom.getNumber());
    verify(roomRepository, times(1)).findById(1L);
  }

  @Test
  void getRoomById_WhenRoomDoesNotExist_ShouldThrowException() {
    when(roomRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> roomService.getRoomById(1L));
    verify(roomRepository, times(1)).findById(1L);
  }

  @Test
  void createRoom_ShouldSaveAndReturnRoom() {
    when(roomRepository.save(any(Room.class))).thenReturn(testRoom);

    Room savedRoom = roomService.createRoom(testRoom);

    assertNotNull(savedRoom);
    assertEquals(testRoom.getId(), savedRoom.getId());
    assertEquals(testRoom.getNumber(), savedRoom.getNumber());
    verify(roomRepository, times(1)).save(testRoom);
  }
} 