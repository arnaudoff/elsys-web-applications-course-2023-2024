CREATE TABLE locations
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    name      VARCHAR(50)           NOT NULL,
    latitude  FLOAT                 NOT NULL,
    longitude FLOAT                 NOT NULL,
    CONSTRAINT pk_locations PRIMARY KEY (id)
);

CREATE TABLE sensors_data
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    irradiance          FLOAT                 NOT NULL,
    azimuth             FLOAT                 NOT NULL,
    azimuth_deviation   FLOAT                 NOT NULL,
    elevation           FLOAT                 NOT NULL,
    elevation_deviation FLOAT                 NOT NULL,
    timestamp           datetime              NOT NULL,
    solar_tracker_id    BIGINT                NOT NULL,
    CONSTRAINT pk_sensors_data PRIMARY KEY (id)
);

CREATE TABLE solar_trackers
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    serial_number     VARCHAR(255)          NOT NULL,
    installation_date datetime              NOT NULL,
    location_id       BIGINT                NULL,
    CONSTRAINT pk_solar_trackers PRIMARY KEY (id)
);

CREATE TABLE user_locations
(
    location_id BIGINT NOT NULL,
    user_id     BIGINT NOT NULL,
    CONSTRAINT pk_user_locations PRIMARY KEY (location_id, user_id)
);

CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    email    VARCHAR(255)          NOT NULL,
    username VARCHAR(20)           NOT NULL,
    password VARCHAR(72)           NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE solar_trackers
    ADD CONSTRAINT uc_solar_trackers_serialnumber UNIQUE (serial_number);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE sensors_data
    ADD CONSTRAINT FK_SENSORS_DATA_ON_SOLAR_TRACKER FOREIGN KEY (solar_tracker_id) REFERENCES solar_trackers (id);

ALTER TABLE solar_trackers
    ADD CONSTRAINT FK_SOLAR_TRACKERS_ON_LOCATION FOREIGN KEY (location_id) REFERENCES locations (id);

ALTER TABLE user_locations
    ADD CONSTRAINT fk_useloc_on_location FOREIGN KEY (location_id) REFERENCES locations (id);

ALTER TABLE user_locations
    ADD CONSTRAINT fk_useloc_on_user FOREIGN KEY (user_id) REFERENCES users (id);