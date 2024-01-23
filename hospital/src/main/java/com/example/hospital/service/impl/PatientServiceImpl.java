package com.example.hospital.service.impl;

import com.example.hospital.controller.resource.PatientResource;
import com.example.hospital.repository.PatientRepository;
import com.example.hospital.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.hospital.mapper.PatientMapper.PATIENT_MAPPER;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    @Override
    public List<PatientResource> findAll() {
        return PATIENT_MAPPER.toPatientResources(patientRepository.findAll());
    }

    @Override
    public PatientResource findById(Long id) {
        return PATIENT_MAPPER.toPatientResource(patientRepository.findById(id).orElse(null));
    }

    @Override
    public PatientResource findByName(String name) {
        return PATIENT_MAPPER.toPatientResource(patientRepository.findByName(name));
    }

    @Override
    public PatientResource save(PatientResource patient) {
        System.out.println(patient);
        return PATIENT_MAPPER.toPatientResource(patientRepository.save(PATIENT_MAPPER.fromPatientResource(patient)));
    }

    @Override
    public PatientResource update(PatientResource patient) {
        return PATIENT_MAPPER.toPatientResource(patientRepository.save(PATIENT_MAPPER.fromPatientResource(patient)));
    }

    @Override
    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        patientRepository.deleteAll();
    }
}
