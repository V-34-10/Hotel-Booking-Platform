package com.example.hotel_8.service;

import com.example.hotel_8.entity.Hotel;
import com.example.hotel_8.repository.HotelRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

  @Mock
  private HotelRepository hotelRepository;

  @InjectMocks
  private HotelService hotelService;

  private Hotel testHotel;

  @BeforeEach
  void setUp() {
    testHotel = new Hotel();
    testHotel.setId(1L);
    testHotel.setName("Test Hotel");
  }

  @Test
  void getAllHotels_ShouldReturnListOfHotels() {
    List<Hotel> expectedHotels = Arrays.asList(testHotel);
    when(hotelRepository.findAll()).thenReturn(expectedHotels);

    List<Hotel> actualHotels = hotelService.getAllHotels();

    assertNotNull(actualHotels);
    assertEquals(expectedHotels.size(), actualHotels.size());
    assertEquals(expectedHotels.get(0).getName(), actualHotels.get(0).getName());
    verify(hotelRepository, times(1)).findAll();
  }

  @Test
  void getHotelById_WhenHotelExists_ShouldReturnHotel() {
    when(hotelRepository.findById(1L)).thenReturn(Optional.of(testHotel));

    Hotel actualHotel = hotelService.getHotelById(1L);

    assertNotNull(actualHotel);
    assertEquals(testHotel.getId(), actualHotel.getId());
    assertEquals(testHotel.getName(), actualHotel.getName());
    verify(hotelRepository, times(1)).findById(1L);
  }

  @Test
  void getHotelById_WhenHotelDoesNotExist_ShouldThrowException() {
    when(hotelRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> hotelService.getHotelById(1L));
    verify(hotelRepository, times(1)).findById(1L);
  }

  @Test
  void createHotel_ShouldReturnSavedHotel() {
    when(hotelRepository.save(any(Hotel.class))).thenReturn(testHotel);

    Hotel savedHotel = hotelService.createHotel(testHotel);

    assertNotNull(savedHotel);
    assertEquals(testHotel.getId(), savedHotel.getId());
    assertEquals(testHotel.getName(), savedHotel.getName());
    verify(hotelRepository, times(1)).save(testHotel);
  }
} 