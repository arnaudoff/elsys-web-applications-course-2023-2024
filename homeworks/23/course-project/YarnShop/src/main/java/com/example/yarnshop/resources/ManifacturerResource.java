package com.example.yarnshop.resources;

import com.example.yarnshop.entity.Manifacturer;
import com.example.yarnshop.service.ManifacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manifacturers")
public class ManifacturerResource {
    private final ManifacturerService manifacturerService;

    @Autowired
    public ManifacturerResource(ManifacturerService manifacturerService) {
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
