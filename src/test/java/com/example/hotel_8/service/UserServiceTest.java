package com.example.hotel_8.service;

import com.example.hotel_8.entity.User;
import com.example.hotel_8.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @InjectMocks
  private UserService userService;

  private User testUser;

  @BeforeEach
  void setUp() {
    testUser = new User();
    testUser.setId(1L);
    testUser.setUsername("testuser");
    testUser.setPassword("password");
  }

  @Test
  void loadUserByUsername_WhenUserExists_ShouldReturnUserDetails() {
    when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(testUser));

    UserDetails userDetails = userService.loadUserByUsername("testuser");

    assertNotNull(userDetails);
    assertEquals(testUser.getUsername(), userDetails.getUsername());
    verify(userRepository, times(1)).findByUsername("testuser");
  }

  @Test
  void loadUserByUsername_WhenUserDoesNotExist_ShouldThrowException() {
    when(userRepository.findByUsername("nonexistent")).thenReturn(Optional.empty());

    assertThrows(UsernameNotFoundException.class,
        () -> userService.loadUserByUsername("nonexistent"));
    verify(userRepository, times(1)).findByUsername("nonexistent");
  }

  @Test
  void registerUser_WhenUsernameDoesNotExist_ShouldSaveAndReturnUser() {
    when(userRepository.existsByUsername("testuser")).thenReturn(false);
    when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
    when(userRepository.save(any(User.class))).thenReturn(testUser);

    User registeredUser = userService.registerUser(testUser);

    assertNotNull(registeredUser);
    assertEquals("USER", registeredUser.getRole());
    assertEquals("encodedPassword", registeredUser.getPassword());
    verify(userRepository, times(1)).existsByUsername("testuser");
    verify(passwordEncoder, times(1)).encode("password");
    verify(userRepository, times(1)).save(testUser);
  }

  @Test
  void registerUser_WhenUsernameExists_ShouldThrowException() {
    when(userRepository.existsByUsername("testuser")).thenReturn(true);

    assertThrows(RuntimeException.class, () -> userService.registerUser(testUser));
    verify(userRepository, times(1)).existsByUsername("testuser");
    verify(userRepository, never()).save(any(User.class));
  }
} 