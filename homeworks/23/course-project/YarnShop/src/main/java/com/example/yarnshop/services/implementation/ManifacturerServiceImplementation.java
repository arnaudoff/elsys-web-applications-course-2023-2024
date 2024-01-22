package com.example.yarnshop.services.implementation;

import com.example.yarnshop.entity.Manifacturer;
import com.example.yarnshop.repository.ManifacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManifacturerServiceImplementation implements com.example.yarnshop.service.ManifacturerService {
    private final ManifacturerRepository manifacturerRepository;

    @Autowired
    public ManifacturerServiceImplementation(ManifacturerRepository manifacturerRepository) {
        this.manifacturerRepository = manifacturerRepository;
    }

    @Override
    public List<Manifacturer> getAllManifacturers() {
        return manifacturerRepository.findAll();
    }

    @Override
    public Manifacturer getManifacturerById(Long id) {
        Optional<Manifacturer> optionalManifacturer = manifacturerRepository.findById(id);
        return optionalManifacturer.orElse(null);
    }

    @Override
    public Manifacturer createManifacturer(Manifacturer manifacturer) {
        return manifacturerRepository.save(manifacturer);
    }

    @Override
    public Manifacturer updateManifacturer(Long id, Manifacturer manifacturer) {
        Optional<Manifacturer> optionalManifacturer = manifacturerRepository.findById(id);
        if (optionalManifacturer.isPresent()) {
            Manifacturer existingManifacturer = optionalManifacturer.get();
            existingManifacturer.setName(manifacturer.getName());
            existingManifacturer.setLocation(manifacturer.getLocation());
            return manifacturerRepository.save(existingManifacturer);
        }
        return null;
    }

    @Override
    public void deleteManifacturer(Long id) {
        manifacturerRepository.deleteById(id);
    }
}
