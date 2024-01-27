package com.example.hospital.mapper;

import com.example.hospital.controller.resource.PatientResource;
import com.example.hospital.entity.Patient;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-22T21:49:18+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
public class PatientMapperImpl implements PatientMapper {

    @Override
    public PatientResource toPatientResource(Patient patient) {
        if ( patient == null ) {
            return null;
        }

        PatientResource patientResource = new PatientResource();

        patientResource.setId( patient.getId() );
        patientResource.setName( patient.getName() );
        patientResource.setAge( patient.getAge() );
        patientResource.setEGN( patient.getEGN() );
        patientResource.setDisease( patient.getDisease() );

        return patientResource;
    }

    @Override
    public Patient fromPatientResource(PatientResource patientResource) {
        if ( patientResource == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setId( patientResource.getId() );
        patient.setName( patientResource.getName() );
        patient.setAge( patientResource.getAge() );
        patient.setEGN( patientResource.getEGN() );
        patient.setDisease( patientResource.getDisease() );

        return patient;
    }

    @Override
    public List<PatientResource> toPatientResources(List<Patient> patients) {
        if ( patients == null ) {
            return null;
        }

        List<PatientResource> list = new ArrayList<PatientResource>( patients.size() );
        for ( Patient patient : patients ) {
            list.add( toPatientResource( patient ) );
        }

        return list;
    }

    @Override
    public List<Patient> fromPatientResources(List<PatientResource> patientResources) {
        if ( patientResources == null ) {
            return null;
        }

        List<Patient> list = new ArrayList<Patient>( patientResources.size() );
        for ( PatientResource patientResource : patientResources ) {
            list.add( fromPatientResource( patientResource ) );
        }

        return list;
    }
}
