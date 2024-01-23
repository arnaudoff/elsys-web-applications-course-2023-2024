package com.example.hospital.mapper;

import com.example.hospital.controller.resource.DoctorResource;
import com.example.hospital.entity.Doctor;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-22T21:49:19+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
public class DoctorMapperImpl implements DoctorMapper {

    @Override
    public DoctorResource toDoctorResource(Doctor doctor) {
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

    @Override
    public Doctor fromDoctorResource(DoctorResource doctorResource) {
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

    @Override
    public List<DoctorResource> toDoctorResources(List<Doctor> doctors) {
        if ( doctors == null ) {
            return null;
        }

        List<DoctorResource> list = new ArrayList<DoctorResource>( doctors.size() );
        for ( Doctor doctor : doctors ) {
            list.add( toDoctorResource( doctor ) );
        }

        return list;
    }

    @Override
    public List<Doctor> fromDoctorResources(List<DoctorResource> doctorResources) {
        if ( doctorResources == null ) {
            return null;
        }

        List<Doctor> list = new ArrayList<Doctor>( doctorResources.size() );
        for ( DoctorResource doctorResource : doctorResources ) {
            list.add( fromDoctorResource( doctorResource ) );
        }

        return list;
    }
}
