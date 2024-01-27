CREATE TABLE client
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    name     VARCHAR(255) NULL,
    email    VARCHAR(255) NULL,
    phone    VARCHAR(255) NULL,
    address  VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    `role`   VARCHAR(255) NULL,
    CONSTRAINT pk_client PRIMARY KEY (id)
);

CREATE TABLE meal
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    price DOUBLE NULL,
    CONSTRAINT pk_meal PRIMARY KEY (id)
);

CREATE TABLE order_meal
(
    meal_id  BIGINT NOT NULL,
    order_id BIGINT NOT NULL
);

CREATE TABLE orders
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    date      datetime NULL,
    total_price DOUBLE NULL,
    client_id BIGINT NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_CLIENT FOREIGN KEY (client_id) REFERENCES client (id);

ALTER TABLE order_meal
    ADD CONSTRAINT fk_ordmea_on_meal FOREIGN KEY (meal_id) REFERENCES meal (id);

ALTER TABLE order_meal
    ADD CONSTRAINT fk_ordmea_on_order FOREIGN KEY (order_id) REFERENCES orders (id);