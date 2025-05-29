CREATE TABLE IF NOT EXISTS hotels
(
    id
    BIGSERIAL
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
) NOT NULL,
    location VARCHAR
(
    255
) NOT NULL,
    rating DOUBLE PRECISION NOT NULL
    );

-- Створення таблиці кімнат
CREATE TABLE IF NOT EXISTS rooms
(
    id
    BIGSERIAL
    PRIMARY
    KEY,
    number
    VARCHAR
(
    10
) NOT NULL,
    hotel_id BIGINT NOT NULL,
    type VARCHAR
(
    20
) NOT NULL,
    price_per_night DECIMAL
(
    10,
    2
) NOT NULL,
    FOREIGN KEY
(
    hotel_id
) REFERENCES hotels
(
    id
)
    );

-- Створення таблиці бронювань
CREATE TABLE IF NOT EXISTS bookings
(
    id
    BIGSERIAL
    PRIMARY
    KEY,
    room_id
    BIGINT
    NOT
    NULL,
    user_id
    BIGINT
    NOT
    NULL,
    check_in_date
    DATE
    NOT
    NULL,
    check_out_date
    DATE
    NOT
    NULL,
    status
    VARCHAR
(
    20
) NOT NULL,
    FOREIGN KEY
(
    room_id
) REFERENCES rooms
(
    id
),
    FOREIGN KEY
(
    user_id
) REFERENCES users
(
    id
)
    );