package com.example.yarnshop.service;

import com.example.yarnshop.entity.Manifacturer;

import java.util.List;

public interface ManifacturerService {
    List<Manifacturer> getAllManifacturers();
    Manifacturer getManifacturerById(Long id);
    Manifacturer createManifacturer(Manifacturer manifacturer);
    Manifacturer updateManifacturer(Long id, Manifacturer manifacturer);
    void deleteManifacturer(Long id);
}
