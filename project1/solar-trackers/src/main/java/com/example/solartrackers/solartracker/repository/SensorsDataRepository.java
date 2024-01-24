package com.example.solartrackers.solartracker.repository;

import com.example.solartrackers.solartracker.entity.SensorsData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorsDataRepository extends JpaRepository<SensorsData, Long> {
    void deleteBySolarTrackerSerialNumber(String serialNumber);
}
