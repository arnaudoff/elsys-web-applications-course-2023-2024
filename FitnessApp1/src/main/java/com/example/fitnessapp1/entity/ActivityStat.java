package com.example.fitnessapp1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "activity_stat")
@Data
public class ActivityStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "steps", nullable = false)
    private Integer steps;

    @Column(name = "calories_consumed", nullable = false)
    private Integer calories;

    @Column(name = "water", nullable = false)
    private Float water;
}
