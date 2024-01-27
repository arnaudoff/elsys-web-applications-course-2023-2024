package com.example.yarnshop.controller;

import com.example.yarnshop.entity.Manifacturer;
import com.example.yarnshop.service.ManifacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manifacturers")
public class ManifacturerController {
    private final ManifacturerService manifacturerService;

    @Autowired
    public ManifacturerController(ManifacturerService manifacturerService) {
        this.manifacturerService = manifacturerService;
    }

    @GetMapping
    public List<Manifacturer> getAllManifacturers() {
        return manifacturerService.getAllManifacturers();
    }

    @GetMapping("/{id}")
    public Manifacturer getManifacturerById(@PathVariable Long id) {
        return manifacturerService.getManifacturerById(id);
    }

    @PostMapping
    public Manifacturer createManifacturer(@RequestBody Manifacturer manifacturer) {
        return manifacturerService.createManifacturer(manifacturer);
    }

    @PutMapping("/{id}")
    public Manifacturer updateManifacturer(@PathVariable Long id, @RequestBody Manifacturer manifacturer) {
        return manifacturerService.updateManifacturer(id, manifacturer);
    }

    @DeleteMapping("/{id}")
    public void deleteManifacturer(@PathVariable Long id) {
        manifacturerService.deleteManifacturer(id);
    }
}
