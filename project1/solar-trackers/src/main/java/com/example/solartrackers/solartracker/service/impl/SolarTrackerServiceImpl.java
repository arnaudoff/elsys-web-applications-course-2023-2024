package com.example.solartrackers.solartracker.service.impl;

import com.example.solartrackers.shared.exception.InvalidSerialNumberException;
import com.example.solartrackers.shared.exception.SerialNumberAlreadyExistsException;
import com.example.solartrackers.solartracker.entity.SensorsData;
import com.example.solartrackers.solartracker.entity.SolarTracker;
import com.example.solartrackers.solartracker.repository.SensorsDataRepository;
import com.example.solartrackers.solartracker.repository.SolarTrackerRepository;
import com.example.solartrackers.solartracker.dto.AddSensorsDataRequest;
import com.example.solartrackers.solartracker.dto.CreateSTRequest;
import com.example.solartrackers.solartracker.service.SolarTrackerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.example.solartrackers.solartracker.mapper.SolarTrackerMapper.solarTrackerMapper;
import static com.example.solartrackers.solartracker.mapper.SensorsDataMapper.sensorsDataMapper;

@Service
@RequiredArgsConstructor
public class SolarTrackerServiceImpl implements SolarTrackerService {
    private final SolarTrackerRepository solarTrackerRepository;
    private final SensorsDataRepository sensorsDataRepository;

    @Override
    public SolarTracker createSolarTracker(CreateSTRequest stData) {
        SolarTracker solarTracker = solarTrackerMapper.fromCreateSTRequest(stData);

        try {
            return solarTrackerRepository.save(solarTracker);
        } catch (DataIntegrityViolationException e) {
            throw new SerialNumberAlreadyExistsException();
        }
    }

    @Override
    public SensorsData addSensorsData(String serialNumber, AddSensorsDataRequest sensorsData) {
        SolarTracker solarTracker = solarTrackerRepository.findBySerialNumber(serialNumber);

        if (solarTracker == null) {
            throw new InvalidSerialNumberException();
        }

        SensorsData sensors = sensorsDataMapper.fromCreateSensorsDataRequest(sensorsData);
        sensors.setSolarTracker(solarTracker);
        sensorsDataRepository.save(sensors);

        return sensors;
    }

    @Override
    public SolarTracker getSolarTracker(String serialNumber) {
        SolarTracker solarTracker = solarTrackerRepository.findBySerialNumber(serialNumber);

        if (solarTracker == null) {
            throw new InvalidSerialNumberException();
        }

        return solarTracker;
    }

    @Override
    public SolarTracker getSolarTracker(Long id) {
        SolarTracker solarTracker = solarTrackerRepository.findById(id).orElse(null);

        if (solarTracker == null) {
            throw new InvalidSerialNumberException();
        }

        return solarTracker;
    }

    @Override
    public Set<SensorsData> getSensorsData(String serialNumber) {
        SolarTracker solarTracker = getSolarTracker(serialNumber);
        return solarTracker.getSensorsData();
    }

    @Override
    public void deleteSolarTracker(String serialNumber) {
        SolarTracker solarTracker = getSolarTracker(serialNumber);
        solarTrackerRepository.delete(solarTracker);
    }

    @Transactional
    @Override
    public void clearSensorsData(String serialNumber) {
        try {
            sensorsDataRepository.deleteBySolarTrackerSerialNumber(serialNumber);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidSerialNumberException();
        }
    }
}
