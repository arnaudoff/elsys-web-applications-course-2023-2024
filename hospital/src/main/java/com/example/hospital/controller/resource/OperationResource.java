package com.example.hospital.controller.resource;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Data
@Getter
@Setter
public class OperationResource {
    private Long id;
    private PatientResource patient;
    private DoctorResource doctor;
    private Date date;
}
