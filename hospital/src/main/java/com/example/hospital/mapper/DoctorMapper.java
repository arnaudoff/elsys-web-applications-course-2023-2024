package com.example.hospital.mapper;

import com.example.hospital.controller.resource.DoctorResource;
import com.example.hospital.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DoctorMapper {
    DoctorMapper DOCTOR_MAPPER = Mappers.getMapper(DoctorMapper.class);

    DoctorResource toDoctorResource(Doctor doctor);

    Doctor fromDoctorResource(DoctorResource doctorResource);

    List<DoctorResource> toDoctorResources(List<Doctor> doctors);

    List<Doctor> fromDoctorResources(List<DoctorResource> doctorResources);
}
