# Hotel Booking Platform

A Spring Boot application for hotel booking management with JWT authentication and ELK stack integration.

## Features

- Hotel and room management
- Room booking system
- User authentication with JWT
- ELK stack integration for logging
- Responsive web interface

## Prerequisites

- Java 17
- Maven
- PostgreSQL
- Docker and Docker Compose (for ELK stack)

## Database Setup

1. Create a PostgreSQL database named `hotel_db`
2. Update the database configuration in `application.properties` if needed

## Running the Application

1. Clone the repository
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## ELK Stack Setup

1. Start the ELK stack using Docker Compose:
   ```bash
   docker-compose up -d
   ```
2. Access Kibana at http://localhost:5601
3. Access Elasticsearch at http://localhost:9200

## API Endpoints

### Authentication
- POST /api/auth/register - Register a new user
- POST /api/auth/login - Login and get JWT token

### Hotels
- GET /api/hotels - Get all hotels
- GET /api/hotels/{id} - Get hotel by ID
- POST /api/hotels - Create a new hotel

### Rooms
- GET /api/rooms/by-hotel/{hotelId} - Get all rooms for a hotel
- POST /api/rooms - Create a new room

### Bookings
- POST /api/bookings - Create a new booking
- GET /api/bookings/by-guest - Get bookings by guest name
- GET /api/bookings/my-bookings - Get current user's bookings

## Web Interface

The application provides a web interface accessible at:
- http://localhost:8080 - Main page
- http://localhost:8080/login - Login page
- http://localhost:8080/register - Registration page

## Security

- JWT-based authentication
- Password encryption using BCrypt
- Role-based access control

## Logging
```bash
  curl -X GET "localhost:9200/hotel-booking-*/_search?pretty"
```
The application uses the ELK stack for centralized logging:
- Logs are sent to Logstash (port 5044)
- Logs are stored in Elasticsearch
- Logs can be viewed in Kibana