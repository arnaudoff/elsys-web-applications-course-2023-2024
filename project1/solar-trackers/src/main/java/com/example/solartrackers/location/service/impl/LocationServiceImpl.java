package com.example.solartrackers.location.service.impl;

import com.example.solartrackers.location.dto.AddLocationRequest;
import com.example.solartrackers.location.entity.Location;
import com.example.solartrackers.location.repository.LocationRepository;
import com.example.solartrackers.location.dto.UpdateLocationRequest;
import com.example.solartrackers.location.service.LocationService;
import com.example.solartrackers.shared.exception.InvalidIdException;
import com.example.solartrackers.shared.exception.SolarTrackerAlreadyAddedException;
import com.example.solartrackers.shared.exception.SolarTrackerIsTakenException;
import com.example.solartrackers.solartracker.entity.SolarTracker;
import com.example.solartrackers.solartracker.service.SolarTrackerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.solartrackers.location.mapper.LocationMapper.locationMapper;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final SolarTrackerService solarTrackerService;

    @Override
    public Location createLocation(AddLocationRequest locationData) {
        Location location = locationMapper.fromCreateLocationRequest(locationData);

        locationData.getSolarTrackers()
            .forEach(serialNumber -> {
                SolarTracker solarTracker = solarTrackerService.getSolarTracker(serialNumber);

                if (solarTracker.getLocation() != null) {
                    throw new SolarTrackerIsTakenException();
                }

                location.addSolarTracker(solarTracker);
            });

        locationRepository.save(location);

        return location;
    }

    @Override
    public Location getLocation(Long id) {
        Location location = locationRepository.findById(id).orElse(null);

        if (location == null) {
            throw new InvalidIdException("Location ID = " + id + " does not exist");
        }

        return location;
    }

    @Override
    public Location updateLocation(Long id, UpdateLocationRequest locationData) {
        Location location = getLocation(id);

        if (locationData.getName() != null) {
            location.setName(locationData.getName());
        }

        if (locationData.getLatitude() != 0) {
            location.setLatitude(locationData.getLatitude());
        }

        if (locationData.getLongitude() != 0) {
            location.setLongitude(locationData.getLongitude());
        }

        locationRepository.save(location);

        return location;
    }

    @Override
    public Location addSolarTracker(Long id, Long solarTrackerId) {
        Location location = getLocation(id);
        SolarTracker solarTracker = solarTrackerService.getSolarTracker(solarTrackerId);

        if (location.getSolarTrackers().contains(solarTracker)) {
            throw new SolarTrackerAlreadyAddedException();
        }

        if (solarTracker.getLocation() != null) {
            throw new SolarTrackerIsTakenException();
        }

        location.addSolarTracker(solarTracker);
        locationRepository.save(location);

        return location;
    }

    @Override
    public void deleteLocation(Long id) {
        Location location = getLocation(id);
        locationRepository.delete(location);
    }
}
