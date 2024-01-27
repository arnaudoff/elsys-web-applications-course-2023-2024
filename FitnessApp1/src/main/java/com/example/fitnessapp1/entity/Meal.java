package com.example.fitnessapp1.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "meal")
@Data
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    // per 100g
    @Column(name = "calories", nullable = false)
    private Integer calories;

    @Column(name = "protein", nullable = false)
    private Float protein;

    @Column(name = "carbs", nullable = false)
    private Float carbs;

    @Column(name = "fat", nullable = false)
    private Float fat;

    @Column(name = "fiber", nullable = false)
    private Float fiber;

    @Column(name = "sugar", nullable = false)
    private Float sugar;
}
