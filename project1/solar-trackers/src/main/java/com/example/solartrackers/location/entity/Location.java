package com.example.solartrackers.location.entity;

import com.example.solartrackers.solartracker.entity.SolarTracker;
import com.example.solartrackers.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "locations")
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private float latitude;

    @Column(nullable = false)
    private float longitude;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SolarTracker> solarTrackers = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "locations")
    private Set<User> users = new HashSet<>();

    public void addSolarTracker(SolarTracker solarTracker) {
        solarTrackers.add(solarTracker);
        solarTracker.setLocation(this);
    }

    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location location)) return false;

        return Objects.equals(id, location.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
