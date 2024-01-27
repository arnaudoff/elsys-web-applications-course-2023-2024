package com.example.hospital.service;

import com.example.hospital.controller.resource.DoctorResource;
import com.example.hospital.entity.Operation;

import java.util.List;

public interface DoctorService {
    List<DoctorResource> findAll();
    DoctorResource findById(Long id);
    DoctorResource findByName(String name);
    List<DoctorResource> findAllBySpecialization(String specialization);
    boolean existsByName(String name);
    DoctorResource save(DoctorResource doctorResource);
    DoctorResource update(DoctorResource doctorResource);
    void deleteById(Long id);
    void deleteAll();
}
