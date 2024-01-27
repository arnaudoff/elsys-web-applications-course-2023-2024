package com.example.hospital.mapper;
import com.example.hospital.controller.resource.PatientResource;
import com.example.hospital.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PatientMapper {
    PatientMapper PATIENT_MAPPER = Mappers.getMapper(PatientMapper.class);

    PatientResource toPatientResource(Patient patient);

    Patient fromPatientResource(PatientResource patientResource);

    List<PatientResource> toPatientResources(List<Patient> patients);

    List<Patient> fromPatientResources(List<PatientResource> patientResources);
}
