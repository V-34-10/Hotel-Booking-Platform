package com.example.hotel_8.dto;

import lombok.Data;

@Data
public class RoomCreateRequest {
  private String number;
  private String type;
  private Double pricePerNight;
}