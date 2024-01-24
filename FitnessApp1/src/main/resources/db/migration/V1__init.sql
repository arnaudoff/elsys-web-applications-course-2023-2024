CREATE TABLE activity_stat
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    user_id           BIGINT        NOT NULL,
    date              date          NOT NULL,
    steps             INT           NOT NULL,
    calories_consumed INT           NOT NULL,
    water             numeric(3, 1) NOT NULL,
    CONSTRAINT pk_activity_stat PRIMARY KEY (id)
);

CREATE TABLE meal
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    name     VARCHAR(32)   NOT NULL,
    calories INT           NOT NULL,
    protein  numeric(5, 2) NOT NULL,
    carbs    numeric(5, 2) NOT NULL,
    fat      numeric(5, 2) NOT NULL,
    fiber    numeric(5, 2) NOT NULL,
    sugar    numeric(5, 2) NOT NULL,
    CONSTRAINT pk_meal PRIMARY KEY (id)
);

CREATE TABLE meal_stat
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    user_id BIGINT        NOT NULL,
    meal_id BIGINT        NOT NULL,
    portion numeric(3, 1) NOT NULL,
    type    VARCHAR(9)    NOT NULL,
    date    date          NOT NULL,
    CONSTRAINT pk_meal_stat PRIMARY KEY (id)
);

CREATE TABLE profile
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    user_id       BIGINT        NOT NULL,
    date_of_birth date          NOT NULL,
    gender        VARCHAR(6)    NOT NULL,
    height        numeric(4, 1) NOT NULL,
    weight        numeric(4, 1) NOT NULL,
    goal_calories INT           NOT NULL,
    goal_weight   numeric(4, 1) NOT NULL,
    goal_steps    INT           NOT NULL,
    goal_water    numeric(3, 1) NOT NULL,
    CONSTRAINT pk_profile PRIMARY KEY (id)
);

CREATE TABLE user
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    username VARCHAR(16) NOT NULL,
    password VARCHAR(64) NOT NULL,
    `role`   VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE meal
    ADD CONSTRAINT uc_meal_name UNIQUE (name);

ALTER TABLE profile
    ADD CONSTRAINT uc_profile_user UNIQUE (user_id);

ALTER TABLE user
    ADD CONSTRAINT uc_user_username UNIQUE (username);

ALTER TABLE activity_stat
    ADD CONSTRAINT FK_ACTIVITY_STAT_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE meal_stat
    ADD CONSTRAINT FK_MEAL_STAT_ON_MEAL FOREIGN KEY (meal_id) REFERENCES meal (id);

ALTER TABLE meal_stat
    ADD CONSTRAINT FK_MEAL_STAT_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE profile
    ADD CONSTRAINT FK_PROFILE_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);