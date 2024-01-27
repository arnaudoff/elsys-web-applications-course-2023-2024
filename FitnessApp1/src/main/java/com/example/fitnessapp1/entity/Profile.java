package com.example.fitnessapp1.entity;

import com.example.fitnessapp1.shared.Gender;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "profile")
@Data
@Builder
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "height", nullable = false)
    private Float height;

    @Column(name = "weight", nullable = false)
    private Float weight;

//    @Column(name = "BMI", columnDefinition = "NUMERIC(3, 1)")
//    private Float BMI;
//
//    @Column(name = "body_fat", columnDefinition = "NUMERIC(3, 1)")
//    private Float bodyFat;

    @Column(name = "goal_calories", nullable = false)
    private Integer goalCalories;

    @Column(name = "goal_weight", nullable = false)
    private Float goalWeight;

    @Column(name = "goal_steps", nullable = false)
    private Integer goalSteps;

    @Column(name = "goal_water", nullable = false)
    private Float goalWater;
}