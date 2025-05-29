package com.example.hotel_8.dto;
import lombok.Data;
@Data
public class HotelCreateRequest {
  private String name;
  private String location;
  private Double rating;
}
