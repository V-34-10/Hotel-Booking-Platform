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
    address VARCHAR
(
    255
) NOT NULL,
    rating DOUBLE PRECISION
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