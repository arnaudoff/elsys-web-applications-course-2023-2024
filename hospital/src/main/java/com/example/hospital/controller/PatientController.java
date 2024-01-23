package com.example.hospital.controller;

import com.example.hospital.controller.resource.PatientResource;
import com.example.hospital.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/hospital/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PatientResource patientResource) {
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/api/v1/patient/{id}")
                .buildAndExpand(patientService.save(patientResource).getId()).toUri()).build();
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody PatientResource patientResource) {
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/api/v1/patient/{id}")
                .buildAndExpand(patientService.update(patientResource).getId()).toUri()).build();
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestBody Long id) {
        patientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll() {
        patientService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(patientService.findAll());
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestBody Long id) {
        return ResponseEntity.ok(patientService.findById(id));
    }

    @GetMapping("/findByName")
    public ResponseEntity<?> findByName(@RequestBody String name) {
        return ResponseEntity.ok(patientService.findByName(name));
    }
}
