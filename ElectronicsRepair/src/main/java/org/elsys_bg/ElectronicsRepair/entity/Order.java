package org.elsys_bg.ElectronicsRepair.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "Orders")
@Data
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "supportedDeviceTypeId")
    private SupportedDeviceForRepair supportedDeviceType;

    private String model;

    private String description;

    @ManyToOne
    @JoinColumn(name = "orderStatus")
    private OrderStatus orderStatus;
}