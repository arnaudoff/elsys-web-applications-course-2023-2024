package com.example.solartrackers.solartracker.service;

import com.example.solartrackers.solartracker.entity.SensorsData;
import com.example.solartrackers.solartracker.entity.SolarTracker;
import com.example.solartrackers.solartracker.dto.AddSensorsDataRequest;
import com.example.solartrackers.solartracker.dto.CreateSTRequest;

import java.util.Set;

public interface SolarTrackerService {
    SolarTracker createSolarTracker(CreateSTRequest stData);
    SensorsData addSensorsData(String serialNumber, AddSensorsDataRequest sensorsData);
    SolarTracker getSolarTracker(String serialNumber);
    SolarTracker getSolarTracker(Long id);
    Set<SensorsData> getSensorsData(String serialNumber);
    void deleteSolarTracker(String serialNumber);
    void clearSensorsData(String serialNumber);
}
