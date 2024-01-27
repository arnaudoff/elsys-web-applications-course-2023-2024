package org.elsys_bg.ElectronicsRepair.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "SupportedDevicesForRepair")
@Data
public class SupportedDeviceForRepair{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "deviceTypeId")
    private DeviceType deviceType;

    private String manufacturer;
}