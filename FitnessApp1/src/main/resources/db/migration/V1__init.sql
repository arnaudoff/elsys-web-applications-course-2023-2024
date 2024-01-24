CREATE TABLE activity_stat
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    user_id           BIGINT NOT NULL,
    date              date   NOT NULL,
    steps             INT    NOT NULL,
    calories_consumed INT    NOT NULL,
    water             FLOAT  NOT NULL,
    CONSTRAINT pk_activity_stat PRIMARY KEY (id)
);

CREATE TABLE meal
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    name     VARCHAR(255) NOT NULL,
    calories INT          NOT NULL,
    protein  FLOAT        NOT NULL,
    carbs    FLOAT        NOT NULL,
    fat      FLOAT        NOT NULL,
    fiber    FLOAT        NOT NULL,
    sugar    FLOAT        NOT NULL,
    CONSTRAINT pk_meal PRIMARY KEY (id)
);

CREATE TABLE meal_stat
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    user_id BIGINT       NOT NULL,
    meal_id BIGINT       NOT NULL,
    portion FLOAT        NOT NULL,
    type    VARCHAR(255) NOT NULL,
    date    date         NOT NULL,
    CONSTRAINT pk_meal_stat PRIMARY KEY (id)
);

CREATE TABLE profile
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    user_id       BIGINT       NOT NULL,
    date_of_birth date         NOT NULL,
    gender        VARCHAR(255) NOT NULL,
    height        FLOAT        NOT NULL,
    weight        FLOAT        NOT NULL,
    goal_calories INT          NOT NULL,
    goal_weight   FLOAT        NOT NULL,
    goal_steps    INT          NOT NULL,
    goal_water    FLOAT        NOT NULL,
    CONSTRAINT pk_profile PRIMARY KEY (id)
);

CREATE TABLE user
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
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