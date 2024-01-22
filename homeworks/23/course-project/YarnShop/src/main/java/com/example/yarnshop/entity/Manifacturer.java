package com.example.yarnshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Manifacturer {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String location;

}
