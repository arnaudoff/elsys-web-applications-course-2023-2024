package com.example.solartrackers.solartracker.entity;

import com.example.solartrackers.location.entity.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "solar_trackers")
@Data
public class SolarTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false, unique = true)
    private String serialNumber;

    @Column(nullable = false)
    private Date installationDate;

    // TODO: here (add cron)
//    @Column(nullable = false)
//    private boolean isActive;

    @JsonIgnore
    @OneToMany(mappedBy = "solarTracker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SensorsData> sensorsData;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SolarTracker that)) return false;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
