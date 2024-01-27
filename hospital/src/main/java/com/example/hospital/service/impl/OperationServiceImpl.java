package com.example.hospital.service.impl;

import com.example.hospital.controller.resource.OperationResource;
import com.example.hospital.repository.OperationRepository;
import com.example.hospital.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import static com.example.hospital.mapper.OperationMapper.OPERATION_MAPPER;

@Service
@RequiredArgsConstructor

public class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;
    @Override
    public List<OperationResource> findAll() {
        return OPERATION_MAPPER.toOperationResources(operationRepository.findAll());
    }

    @Override
    public List<OperationResource> findAllByPatientId(Long patientId) {
        return OPERATION_MAPPER.toOperationResources(operationRepository.findAllByPatientId(patientId));
    }

    @Override
    public List<OperationResource> findAllByDoctorId(Long doctorId) {
        return OPERATION_MAPPER.toOperationResources(operationRepository.findAllByDoctorId(doctorId));
    }

    @Override
    public OperationResource findById(Long id) {
        return OPERATION_MAPPER.toOperationResource(operationRepository.findById(id).orElse(null));
    }

    @Override
    public OperationResource save(OperationResource operationResource) {
        return OPERATION_MAPPER.toOperationResource(operationRepository.save(OPERATION_MAPPER.fromOperationResource(operationResource)));
    }

    @Override
    public OperationResource update(OperationResource operationResource) {
        return OPERATION_MAPPER.toOperationResource(operationRepository.save(OPERATION_MAPPER.fromOperationResource(operationResource)));
    }

    @Override
    public List<OperationResource> findAllByPatientIdAndDoctorId(Long patientId, Long doctorId) {
        return OPERATION_MAPPER.toOperationResources(operationRepository.findAllByPatientIdAndDoctorId(patientId, doctorId));
    }


    @Override
    public List<OperationResource> findAllByDoctorIdAndDate(Long doctorId, Date date) {
        return OPERATION_MAPPER.toOperationResources(operationRepository.findAllByDoctorIdAndDate(doctorId, date));
    }

    @Override
    public List<OperationResource> findAllByDate(Date date) {
        return OPERATION_MAPPER.toOperationResources(operationRepository.findAllByDate(date));
    }

    @Override
    public void deleteById(Long id) {
        operationRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        operationRepository.deleteAll();
    }
}
