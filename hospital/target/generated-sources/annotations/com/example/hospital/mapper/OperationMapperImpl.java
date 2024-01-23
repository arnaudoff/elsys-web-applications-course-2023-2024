package com.example.hospital.mapper;

import com.example.hospital.controller.resource.DoctorResource;
import com.example.hospital.controller.resource.OperationResource;
import com.example.hospital.controller.resource.PatientResource;
import com.example.hospital.entity.Doctor;
import com.example.hospital.entity.Operation;
import com.example.hospital.entity.Patient;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-22T21:49:19+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
public class OperationMapperImpl implements OperationMapper {

    @Override
    public OperationResource toOperationResource(Operation operation) {
        if ( operation == null ) {
            return null;
        }

        OperationResource operationResource = new OperationResource();

        operationResource.setId( operation.getId() );
        operationResource.setPatient( patientToPatientResource( operation.getPatient() ) );
        operationResource.setDoctor( doctorToDoctorResource( operation.getDoctor() ) );
        operationResource.setDate( operation.getDate() );

        return operationResource;
    }

    @Override
    public Operation fromOperationResource(OperationResource operationResource) {
        if ( operationResource == null ) {
            return null;
        }

        Operation operation = new Operation();

        operation.setId( operationResource.getId() );
        operation.setPatient( patientResourceToPatient( operationResource.getPatient() ) );
        operation.setDoctor( doctorResourceToDoctor( operationResource.getDoctor() ) );
        operation.setDate( operationResource.getDate() );

        return operation;
    }

    @Override
    public List<OperationResource> toOperationResources(List<Operation> operations) {
        if ( operations == null ) {
            return null;
        }

        List<OperationResource> list = new ArrayList<OperationResource>( operations.size() );
        for ( Operation operation : operations ) {
            list.add( toOperationResource( operation ) );
        }

        return list;
    }

    @Override
    public List<Operation> fromOperationResources(List<OperationResource> operationResources) {
        if ( operationResources == null ) {
            return null;
        }

        List<Operation> list = new ArrayList<Operation>( operationResources.size() );
        for ( OperationResource operationResource : operationResources ) {
            list.add( fromOperationResource( operationResource ) );
        }

        return list;
    }

    protected PatientResource patientToPatientResource(Patient patient) {
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

    protected DoctorResource doctorToDoctorResource(Doctor doctor) {
        if ( doctor == null ) {
            return null;
        }

        DoctorResource doctorResource = new DoctorResource();

        doctorResource.setId( doctor.getId() );
        doctorResource.setName( doctor.getName() );
        doctorResource.setAge( doctor.getAge() );
        doctorResource.setEGN( doctor.getEGN() );
        doctorResource.setSpecialty( doctor.getSpecialty() );

        return doctorResource;
    }

    protected Patient patientResourceToPatient(PatientResource patientResource) {
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

    protected Doctor doctorResourceToDoctor(DoctorResource doctorResource) {
        if ( doctorResource == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setId( doctorResource.getId() );
        doctor.setName( doctorResource.getName() );
        doctor.setAge( doctorResource.getAge() );
        doctor.setEGN( doctorResource.getEGN() );
        doctor.setSpecialty( doctorResource.getSpecialty() );

        return doctor;
    }
}
