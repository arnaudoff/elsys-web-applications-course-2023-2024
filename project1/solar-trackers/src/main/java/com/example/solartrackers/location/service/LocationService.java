package com.example.solartrackers.location.service;

import com.example.solartrackers.location.entity.Location;
import com.example.solartrackers.location.dto.AddLocationRequest;
import com.example.solartrackers.location.dto.UpdateLocationRequest;
import com.example.solartrackers.user.entity.User;

public interface LocationService {
    Location createLocation(AddLocationRequest locationData);
    Location getLocation(Long id);
    Location updateLocation(Long id, UpdateLocationRequest locationData);
    Location addSolarTracker(Long id, Long solarTrackerId);
    void deleteLocation(Long id);
}
