package com.example.hotel_8.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingRequestDTO {

  private Long roomId;
  private String guestName;
  private LocalDate fromDate;
  private LocalDate toDate;
}
