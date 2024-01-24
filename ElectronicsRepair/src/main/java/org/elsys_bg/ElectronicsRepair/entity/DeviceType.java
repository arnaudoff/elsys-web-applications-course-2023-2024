package org.elsys_bg.ElectronicsRepair.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "DevicesTypes")
@Data
public class DeviceType{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String deviceType;
}