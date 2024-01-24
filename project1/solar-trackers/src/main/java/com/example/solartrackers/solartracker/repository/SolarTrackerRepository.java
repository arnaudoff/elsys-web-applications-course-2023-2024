package com.example.solartrackers.solartracker.repository;

import com.example.solartrackers.solartracker.entity.SensorsData;
import com.example.solartrackers.solartracker.entity.SolarTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolarTrackerRepository extends JpaRepository<SolarTracker, Long> {
    SolarTracker findBySerialNumber(String serialNumber);
}
