package com.example.hotel_8.controller;

import com.example.hotel_8.dto.RegisterRequest;
import com.example.hotel_8.entity.User;
import com.example.hotel_8.service.UserService;
import com.example.hotel_8.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
    try {
      User user = new User();
      user.setUsername(request.getUsername());
      user.setPassword(request.getPassword());

      User registeredUser = userService.registerUser(user);

      Map<String, Object> response = new HashMap<>();
      response.put("message", "User registered successfully");
      response.put("username", registeredUser.getUsername());

      return ResponseEntity.ok(response);
    } catch (Exception e) {
      Map<String, String> error = new HashMap<>();
      error.put("error", e.getMessage());
      return ResponseEntity.badRequest().body(error);
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              credentials.get("username"),
              credentials.get("password")
          )
      );

      String token = jwtTokenProvider.generateToken(authentication);
      Map<String, String> response = new HashMap<>();
      response.put("token", token);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      Map<String, String> error = new HashMap<>();
      error.put("error", "Invalid username or password");
      return ResponseEntity.badRequest().body(error);
    }
  }
} 