package com.example.hotel_8.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingResponseDTO {

  private Long id;
  private String hotelName;
  private String roomNumber;
  private String guestName;
  private LocalDate fromDate;
  private LocalDate toDate;
}
