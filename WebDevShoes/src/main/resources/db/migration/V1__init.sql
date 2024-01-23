CREATE TABLE review
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    comment VARCHAR(255)          NULL,
    rating  INT                   NOT NULL,
    user_id BIGINT                NULL,
    shoe_id BIGINT                NULL,
    CONSTRAINT pk_review PRIMARY KEY (id)
);

CREATE TABLE shoe
(
    id    BIGINT AUTO_INCREMENT NOT NULL,
    model VARCHAR(255)          NULL,
    brand VARCHAR(255)          NULL,
    color VARCHAR(255)          NULL,
    size  VARCHAR(255)          NULL,
    price DECIMAL               NULL,
    CONSTRAINT pk_shoe PRIMARY KEY (id)
);

CREATE TABLE user
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    username VARCHAR(255)          NULL,
    password VARCHAR(255)          NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_shoe
(
    shoe_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_user_shoe PRIMARY KEY (shoe_id, user_id)
);

ALTER TABLE review
    ADD CONSTRAINT FK_REVIEW_ON_SHOE FOREIGN KEY (shoe_id) REFERENCES shoe (id);

ALTER TABLE review
    ADD CONSTRAINT FK_REVIEW_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_shoe
    ADD CONSTRAINT fk_user_shoe_on_shoe FOREIGN KEY (shoe_id) REFERENCES shoe (id);

ALTER TABLE user_shoe
    ADD CONSTRAINT fk_user_shoe_on_user FOREIGN KEY (user_id) REFERENCES user (id);