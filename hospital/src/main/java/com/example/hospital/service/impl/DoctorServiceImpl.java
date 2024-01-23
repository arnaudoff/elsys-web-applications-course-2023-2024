package com.example.hospital.service.impl;

import com.example.hospital.controller.resource.DoctorResource;
import com.example.hospital.entity.Operation;
import com.example.hospital.repository.DoctorRepository;
import com.example.hospital.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.hospital.mapper.DoctorMapper.DOCTOR_MAPPER;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    @Override
    public List<DoctorResource> findAll() {
        return DOCTOR_MAPPER.toDoctorResources(doctorRepository.findAll());
    }

    @Override
    public DoctorResource findById(Long id) {
        return DOCTOR_MAPPER.toDoctorResource(doctorRepository.findById(id).orElse(null));
    }

    @Override
    public DoctorResource findByName(String name) {
        return DOCTOR_MAPPER.toDoctorResource(doctorRepository.findByName(name));
    }

    @Override
    public List<DoctorResource> findAllBySpecialization(String specialization) {
        return DOCTOR_MAPPER.toDoctorResources(doctorRepository.findAllBySpecialty(specialization));
    }

    @Override
    public boolean existsByName(String name) {
        return DOCTOR_MAPPER.toDoctorResource(doctorRepository.findByName(name)) != null;
    }

    @Override
    public DoctorResource save(DoctorResource doctorResource) {
        return DOCTOR_MAPPER.toDoctorResource(doctorRepository.save(DOCTOR_MAPPER.fromDoctorResource(doctorResource)));
    }

    @Override
    public DoctorResource update(DoctorResource doctorResource) {
        return DOCTOR_MAPPER.toDoctorResource(doctorRepository.save(DOCTOR_MAPPER.fromDoctorResource(doctorResource)));
    }

    @Override
    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        doctorRepository.deleteAll();
    }
}
