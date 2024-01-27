package com.example.solartrackers.user.entity;

import com.example.solartrackers.location.entity.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 20)
    private String username;

    @JsonIgnore
    @Column(nullable = false, columnDefinition = "VARCHAR(72)")
    private String password; // BCrypt

    @ManyToMany
    @JoinTable(
        name = "user_locations",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private Set<Location> locations = new HashSet<>();

    public void addLocation(Location location) {
        locations.add(location);
        location.addUser(this);
    }
}
