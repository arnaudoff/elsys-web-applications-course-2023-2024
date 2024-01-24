package com.example.solartrackers.user.service.impl;

import com.example.solartrackers.location.dto.AddLocationRequest;
import com.example.solartrackers.location.entity.Location;
import com.example.solartrackers.location.service.LocationService;
import com.example.solartrackers.shared.exception.InvalidIdException;
import com.example.solartrackers.shared.exception.LocationAlreadyAddedException;
import com.example.solartrackers.user.entity.User;
import com.example.solartrackers.user.repository.UserRepository;
import com.example.solartrackers.user.dto.UpdateUserRequest;
import com.example.solartrackers.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LocationService locationService;

    @Override
    public User createUser(User userData) {
        return userRepository.save(userData);
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    private User getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new InvalidIdException("Invalid user id = " + id);
        }

        return user;
    }

    @Override
    public User updateUser(Long id, UpdateUserRequest userData) {
        User user = getUser(id);
        user.setUsername(userData.getUsername());
        userRepository.save(user);

        return user;
    }

    @Transactional
    @Override
    public Location addNewLocation(Long id, AddLocationRequest locationData) {
        User user = getUser(id);
        Location location = locationService.createLocation(locationData);
        user.addLocation(location);
        userRepository.save(user);

        return location;
    }

    @Transactional
    @Override
    public Location addExistingLocation(Long id, Long locationId) {
        User user = getUser(id);
        Location location = locationService.getLocation(locationId);

        if (user.getLocations().contains(location)) {
            throw new LocationAlreadyAddedException();
        }

        user.addLocation(location);
        userRepository.save(user);

        return location;
    }

    @Override
    public Set<Location> getLocations(Long id) {
        User user = getUser(id);
        return user.getLocations();
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUser(id);
        userRepository.delete(user);
    }
}
