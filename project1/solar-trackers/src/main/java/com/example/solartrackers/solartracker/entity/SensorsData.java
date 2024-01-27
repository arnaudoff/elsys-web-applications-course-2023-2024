package com.example.solartrackers.solartracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "sensors_data")
@Data
public class SensorsData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private float irradiance;

    @Column(nullable = false)
    private float azimuth;

    @Column(nullable = false)
    private float azimuthDeviation;

    @Column(nullable = false)
    private float elevation;

    @Column(nullable = false)
    private float elevationDeviation;

    @Column(nullable = false)
    private Date timestamp;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "solar_tracker_id", nullable = false, referencedColumnName = "id")
    private SolarTracker solarTracker;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SensorsData that)) return false;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
