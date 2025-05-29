-- Додавання готелів
INSERT INTO hotels (name, location, rating)
SELECT * FROM (VALUES 
    ('Grand Hotel', 'вул. Шевченка 10, Київ', 4.5),
    ('City Hotel', 'вул. Хрещатик 22, Київ', 4.2),
    ('Seaside Resort', 'вул. Морська 5, Одеса', 4.8),
    ('Mountain View', 'вул. Гірська 15, Львів', 4.3),
    ('Business Hotel', 'вул. Ділова 7, Харків', 4.0)
) AS source (name, location, rating)
WHERE NOT EXISTS (SELECT 1 FROM hotels);

-- Додавання кімнат
INSERT INTO rooms (number, hotel_id, type, price_per_night)
SELECT * FROM (VALUES 
    -- Кімнати для Grand Hotel
    ('101', (SELECT id FROM hotels WHERE name = 'Grand Hotel'), 'STANDARD', 1000),
    ('102', (SELECT id FROM hotels WHERE name = 'Grand Hotel'), 'STANDARD', 1000),
    ('201', (SELECT id FROM hotels WHERE name = 'Grand Hotel'), 'DELUXE', 2000),
    ('202', (SELECT id FROM hotels WHERE name = 'Grand Hotel'), 'DELUXE', 2000),
    ('301', (SELECT id FROM hotels WHERE name = 'Grand Hotel'), 'SUITE', 3000),

    -- Кімнати для City Hotel
    ('101', (SELECT id FROM hotels WHERE name = 'City Hotel'), 'STANDARD', 800),
    ('102', (SELECT id FROM hotels WHERE name = 'City Hotel'), 'STANDARD', 800),
    ('201', (SELECT id FROM hotels WHERE name = 'City Hotel'), 'DELUXE', 1500),
    ('202', (SELECT id FROM hotels WHERE name = 'City Hotel'), 'DELUXE', 1500),

    -- Кімнати для Seaside Resort
    ('101', (SELECT id FROM hotels WHERE name = 'Seaside Resort'), 'STANDARD', 1200),
    ('102', (SELECT id FROM hotels WHERE name = 'Seaside Resort'), 'STANDARD', 1200),
    ('201', (SELECT id FROM hotels WHERE name = 'Seaside Resort'), 'DELUXE', 2500),
    ('202', (SELECT id FROM hotels WHERE name = 'Seaside Resort'), 'DELUXE', 2500),
    ('301', (SELECT id FROM hotels WHERE name = 'Seaside Resort'), 'SUITE', 3500),

    -- Кімнати для Mountain View
    ('101', (SELECT id FROM hotels WHERE name = 'Mountain View'), 'STANDARD', 900),
    ('102', (SELECT id FROM hotels WHERE name = 'Mountain View'), 'STANDARD', 900),
    ('201', (SELECT id FROM hotels WHERE name = 'Mountain View'), 'DELUXE', 1800),
    ('202', (SELECT id FROM hotels WHERE name = 'Mountain View'), 'DELUXE', 1800),

    -- Кімнати для Business Hotel
    ('101', (SELECT id FROM hotels WHERE name = 'Business Hotel'), 'STANDARD', 700),
    ('102', (SELECT id FROM hotels WHERE name = 'Business Hotel'), 'STANDARD', 700),
    ('201', (SELECT id FROM hotels WHERE name = 'Business Hotel'), 'DELUXE', 1300),
    ('202', (SELECT id FROM hotels WHERE name = 'Business Hotel'), 'DELUXE', 1300)
) AS source (number, hotel_id, type, price_per_night)
WHERE NOT EXISTS (SELECT 1 FROM rooms);