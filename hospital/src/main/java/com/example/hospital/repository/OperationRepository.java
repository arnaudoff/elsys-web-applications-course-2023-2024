package com.example.hospital.repository;

import com.example.hospital.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findAllByPatientId(Long patientId);
    List<Operation> findAllByDoctorId(Long doctorId);
    List<Operation> findAllByPatientIdAndDoctorId(Long patientId, Long doctorId);
    List<Operation> findAllByDoctorIdAndDate(Long doctor_id, Date date);
    List<Operation> findAllByDate(Date date);
    void deleteAll();

}
