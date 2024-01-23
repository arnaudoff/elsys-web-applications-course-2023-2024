package com.example.hospital.controller;

import com.example.hospital.controller.resource.OperationResource;
import com.example.hospital.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Date;

@RestController
@RequestMapping("/api/v1/hospital/operation")
@RequiredArgsConstructor
public class OperationController {
    private final OperationService operationService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody OperationResource operationResource) {
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/api/v1/operation/{id}")
                .buildAndExpand(operationService.save(operationResource).getId()).toUri()).build();
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody OperationResource operationResource) {
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/api/v1/operation/{id}")
                .buildAndExpand(operationService.update(operationResource).getId()).toUri()).build();
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestBody Long id) {
        operationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll() {
        operationService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(operationService.findAll());
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestBody Long id) {
        return ResponseEntity.ok(operationService.findById(id));
    }

    @GetMapping("/findByDate")
    public ResponseEntity<?> findByDate(@RequestBody Date date) {
        return ResponseEntity.ok(operationService.findAllByDate(date));
    }

    @GetMapping("/findByDoctorAndDate")
    public ResponseEntity<?> findByDoctorAndDate(@RequestBody Long doctorId, @RequestBody Date date) {
        return ResponseEntity.ok(operationService.findAllByDoctorIdAndDate(doctorId, date));
    }

    @GetMapping("/findByPatientAndDoctor")
    public ResponseEntity<?> findByPatientAndDoctor(@RequestBody Long patientId, @RequestBody Long doctorId) {
        return ResponseEntity.ok(operationService.findAllByPatientIdAndDoctorId(patientId, doctorId));
    }
}
