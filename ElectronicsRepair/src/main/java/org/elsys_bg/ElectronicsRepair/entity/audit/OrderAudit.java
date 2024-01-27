package org.elsys_bg.ElectronicsRepair.entity.audit;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.entity.Client;
import org.elsys_bg.ElectronicsRepair.entity.Order;
import org.elsys_bg.ElectronicsRepair.entity.OrderStatus;
import org.elsys_bg.ElectronicsRepair.entity.SupportedDeviceForRepair;

import java.time.LocalDateTime;

@Entity
@Table(name = "OrderAudit")
@Data
@RequiredArgsConstructor
public class OrderAudit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

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

    @Column(name = "action")
    private char action;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}