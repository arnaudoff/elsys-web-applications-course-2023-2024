package com.example.hospital.controller.resource;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public abstract class PersonResource {
    private Long id;
    private String name;
    private int age;
    private String EGN;


}
