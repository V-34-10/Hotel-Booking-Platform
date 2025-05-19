package com.example.hotel_8.service;

import com.example.hotel_8.entity.Booking;
import com.example.hotel_8.entity.Room;
import com.example.hotel_8.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

  @Mock
  private BookingRepository bookingRepository;

  @InjectMocks
  private BookingService bookingService;

  private Booking testBooking;
  private Room testRoom;

  @BeforeEach
  void setUp() {
    testRoom = new Room();
    testRoom.setId(1L);
    testRoom.setNumber("101");

    testBooking = new Booking();
    testBooking.setId(1L);
    testBooking.setGuestName("John White");
    testBooking.setRoom(testRoom);
    testBooking.setFromDate(LocalDate.now());
    testBooking.setToDate(LocalDate.now().plusDays(3));
  }

  @Test
  void createBooking_ShouldSaveAndReturnBooking() {
    when(bookingRepository.save(any(Booking.class))).thenReturn(testBooking);

    Booking savedBooking = bookingService.createBooking(testBooking);

    assertNotNull(savedBooking);
    assertEquals(testBooking.getId(), savedBooking.getId());
    assertEquals(testBooking.getGuestName(), savedBooking.getGuestName());
    assertEquals(testBooking.getRoom(), savedBooking.getRoom());
    verify(bookingRepository, times(1)).save(testBooking);
  }

  @Test
  void getBookingsByGuestName_ShouldReturnListOfBookings() {
    List<Booking> expectedBookings = Arrays.asList(testBooking);
    when(bookingRepository.findByGuestName("John White")).thenReturn(expectedBookings);

    List<Booking> actualBookings = bookingService.getBookingsByGuestName("John White");

    assertNotNull(actualBookings);
    assertEquals(expectedBookings.size(), actualBookings.size());
    assertEquals(expectedBookings.get(0).getGuestName(), actualBookings.get(0).getGuestName());
    verify(bookingRepository, times(1)).findByGuestName("John White");
  }

  @Test
  void getBookingsByUserId_ShouldReturnListOfBookings() {
    List<Booking> expectedBookings = Arrays.asList(testBooking);
    when(bookingRepository.findByUserId(1L)).thenReturn(expectedBookings);

    List<Booking> actualBookings = bookingService.getBookingsByUserId(1L);

    assertNotNull(actualBookings);
    assertEquals(expectedBookings.size(), actualBookings.size());
    assertEquals(expectedBookings.get(0).getId(), actualBookings.get(0).getId());
    verify(bookingRepository, times(1)).findByUserId(1L);
  }
} 