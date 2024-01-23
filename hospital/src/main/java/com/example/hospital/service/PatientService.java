package com.example.hospital.service;

import com.example.hospital.controller.resource.PatientResource;

import java.util.List;

public interface PatientService {
    List<PatientResource> findAll();
    PatientResource findById(Long id);
    PatientResource findByName(String name);
    PatientResource save(PatientResource patientResource);
    PatientResource update(PatientResource patientResource);
    void deleteById(Long id);
    void deleteAll();
}
