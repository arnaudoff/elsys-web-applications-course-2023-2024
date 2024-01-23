CREATE TABLE doctor
(
    id        BIGINT NOT NULL,
    name      VARCHAR(255) NULL,
    age       INT    NOT NULL,
    egn       VARCHAR(255) NULL,
    specialty VARCHAR(255) NULL,
    CONSTRAINT pk_doctor PRIMARY KEY (id)
);

CREATE TABLE operation
(
    id         BIGINT NOT NULL,
    patient_id BIGINT NULL,
    doctor_id  BIGINT NULL,
    date       date NULL,
    CONSTRAINT pk_operation PRIMARY KEY (id)
);

CREATE TABLE patient
(
    id      BIGINT NOT NULL,
    name    VARCHAR(255) NULL,
    age     INT    NOT NULL,
    egn     VARCHAR(255) NULL,
    disease VARCHAR(255) NULL,
    CONSTRAINT pk_patient PRIMARY KEY (id)
);

ALTER TABLE operation
    ADD CONSTRAINT FK_OPERATION_ON_DOCTOR FOREIGN KEY (doctor_id) REFERENCES doctor (id);

ALTER TABLE operation
    ADD CONSTRAINT FK_OPERATION_ON_PATIENT FOREIGN KEY (patient_id) REFERENCES patient (id);