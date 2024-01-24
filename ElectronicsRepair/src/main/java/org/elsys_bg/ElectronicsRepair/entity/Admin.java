package org.elsys_bg.ElectronicsRepair.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "Admins")
@Data
@RequiredArgsConstructor
public class Admin{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String password;
}