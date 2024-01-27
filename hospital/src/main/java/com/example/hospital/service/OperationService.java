package com.example.hospital.service;

import com.example.hospital.controller.resource.OperationResource;

import java.sql.Date;
import java.util.List;

public interface OperationService {
    List<OperationResource> findAll();
    List<OperationResource> findAllByPatientId(Long patientId);
    List<OperationResource> findAllByDoctorId(Long doctorId);
    OperationResource findById(Long id);
    OperationResource save(OperationResource operationResource);
    OperationResource update(OperationResource operationResource);
    void deleteById(Long id);
    void deleteAll();
    List<OperationResource> findAllByPatientIdAndDoctorId(Long patientId, Long doctorId);
    List<OperationResource> findAllByDoctorIdAndDate(Long doctorId, Date date);
    List<OperationResource> findAllByDate(Date date);
}
