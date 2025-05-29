DELETE
FROM rooms;
DELETE
FROM hotels;

-- Додавання готелів
INSERT INTO hotels (name, location, rating)
VALUES ('Grand Hotel', 'вул. Шевченка 10, Київ', 4.5),
       ('City Hotel', 'вул. Хрещатик 22, Київ', 4.2),
       ('Seaside Resort', 'вул. Морська 5, Одеса', 4.8),
       ('Mountain View', 'вул. Гірська 15, Львів', 4.3),
       ('Business Hotel', 'вул. Ділова 7, Харків', 4.0);

-- Додавання кімнат для кожного готелю
INSERT INTO rooms (number, hotel_id, type, price_per_night)
VALUES
-- Кімнати для Grand Hotel
('101', 1, 'STANDARD', 1000),
('102', 1, 'STANDARD', 1000),
('201', 1, 'DELUXE', 2000),
('202', 1, 'DELUXE', 2000),
('301', 1, 'SUITE', 3000),

-- Кімнати для City Hotel
('101', 2, 'STANDARD', 800),
('102', 2, 'STANDARD', 800),
('201', 2, 'DELUXE', 1500),
('202', 2, 'DELUXE', 1500),

-- Кімнати для Seaside Resort
('101', 3, 'STANDARD', 1200),
('102', 3, 'STANDARD', 1200),
('201', 3, 'DELUXE', 2500),
('202', 3, 'DELUXE', 2500),
('301', 3, 'SUITE', 3500),

-- Кімнати для Mountain View
('101', 4, 'STANDARD', 900),
('102', 4, 'STANDARD', 900),
('201', 4, 'DELUXE', 1800),
('202', 4, 'DELUXE', 1800),

-- Кімнати для Business Hotel
('101', 5, 'STANDARD', 700),
('102', 5, 'STANDARD', 700),
('201', 5, 'DELUXE', 1300),
('202', 5, 'DELUXE', 1300);