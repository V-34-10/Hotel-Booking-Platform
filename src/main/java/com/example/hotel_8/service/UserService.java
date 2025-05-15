package com.example.hotel_8.service;

import com.example.hotel_8.entity.User;
import com.example.hotel_8.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    logger.info("Loading user by username: {}", username);
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> {
          logger.error("User not found with username: {}", username);
          return new UsernameNotFoundException("User not found with username: " + username);
        });
    logger.debug("Found user: {}", username);
    return user;
  }

  public User registerUser(User user) {
    logger.info("Registering new user: {}", user.getUsername());
    if (userRepository.existsByUsername(user.getUsername())) {
      logger.error("Username already exists: {}", user.getUsername());
      throw new RuntimeException("Username already exists");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole("USER");
    User savedUser = userRepository.save(user);
    logger.info("Registered new user with id: {}", savedUser.getId());
    return savedUser;
  }
} 