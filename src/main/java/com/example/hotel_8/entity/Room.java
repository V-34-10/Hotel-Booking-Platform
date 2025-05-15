package com.example.hotel_8.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "rooms")
public class Room {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "hotel_id", nullable = false)
  private Hotel hotel;

  @Column(nullable = false)
  private String number;

  @Column(nullable = false)
  private String type;

  @Column(name = "price_per_night", nullable = false)
  private Double pricePerNight;

  @JsonManagedReference
  @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
  private List<Booking> bookings;
}