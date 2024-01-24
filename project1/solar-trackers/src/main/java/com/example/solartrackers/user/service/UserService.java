package com.example.solartrackers.user.service;

import com.example.solartrackers.location.dto.AddLocationRequest;
import com.example.solartrackers.location.entity.Location;
import com.example.solartrackers.user.entity.User;
import com.example.solartrackers.user.dto.UpdateUserRequest;

import java.util.Set;

public interface UserService {
    User createUser(User userData);
    User getUser(String email);
    User updateUser(Long id, UpdateUserRequest userData);
    Location addNewLocation(Long id, AddLocationRequest locationData);
    Location addExistingLocation(Long id, Long locationId);
    Set<Location> getLocations(Long id);
    void deleteUser(Long id);
}
