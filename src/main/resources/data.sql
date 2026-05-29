INSERT INTO city(
    id,
    name,
    created_at,
    updated_at
)
VALUES
    (
        1,
        'Nagpur',
        NOW(),
        NOW()
    );

-- =========================
-- USERS
-- =========================

INSERT INTO users(
    id,
    name,
    email,
    password,
    user_type,
    city_id,
    created_at,
    updated_at
)
VALUES
    (
        1,
        'Kunal',
        'kunal@gmail.com',
        'pass123',
        'USER',
        1,
        NOW(),
        NOW()
    ),
    (
        2,
        'Prixu',
        'prixu@gmail.com',
        'pass456',
        'ADMIN',
        1,
        NOW(),
        NOW()
    );

-- =========================
-- MOVIES
-- =========================

INSERT INTO movies(id, title, rating, year, director, genre, duration)
VALUES
    (1, 'Interstellar', 4.5, 2026, 'Nolan', 'English', 180);

-- =========================
-- THEATRES
-- =========================

INSERT INTO theatres(id, name, address)
VALUES
    (1, 'PVR Nagpur', 'Nagpur');

-- =========================
-- SCREENS
-- =========================

INSERT INTO screens(id, screen_no, theatre_id)
VALUES
    (1, 1, 1);

-- =========================
-- SEATS
-- =========================

INSERT INTO seats(id, seat_number, row_no, col_no, seat_type, screen_id)
VALUES
    (1, 1, 1, 1, 'GOLD', 1),
    (2, 2, 1, 2, 'GOLD', 1),
    (3, 3, 1, 3, 'GOLD', 1),
    (4, 4, 1, 4,'GOLD', 1),

    (5, 5, 2, 1, 'SILVER', 1),
    (6, 6, 2, 2,'SILVER', 1),
    (7, 7, 2, 3, 'SILVER', 1),
    (8, 8, 2, 4, 'SILVER', 1);

-- =========================
-- SHOWS
-- =========================

INSERT INTO shows(
    id,
    movie_id,
    screen_id,
    start_time,
    end_time
)
VALUES
    (
        1,
        1,
        1,
        '2026-05-29 19:00:00',
        '2026-05-29 22:00:00'
    );

-- =========================
-- SHOW SEATS
-- =========================

INSERT INTO show_seats(
    id,
    show_id,
    seat_id,
    show_seat_status
)
VALUES
    (1, 1, 1, 'AVAILABLE'),
    (2, 1, 2, 'AVAILABLE'),
    (3, 1, 3, 'AVAILABLE'),
    (4, 1, 4, 'AVAILABLE'),

    (5, 1, 5, 'AVAILABLE'),
    (6, 1, 6, 'AVAILABLE'),
    (7, 1, 7, 'AVAILABLE'),
    (8, 1, 8, 'AVAILABLE');

-- =========================
-- SHOW SEAT TYPE PRICING
-- =========================

INSERT INTO show_seat_types(
    id,
    show_id,
    seat_type,
    price
)
VALUES
    (1, 1, 'GOLD', 200),
    (2, 1, 'SILVER', 400);
