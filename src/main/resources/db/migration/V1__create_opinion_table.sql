CREATE TABLE IF NOT EXISTS opinion (

    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    create_date timestamp,
    last_update timestamp,
    user_id int NOT NULL,
    shop_id int NOT NULL,
    purchase_id int NOT NULL UNIQUE,
    comment VARCHAR(500),
    score int NOT NULL,
    deleted BOOLEAN NOT NULL

);