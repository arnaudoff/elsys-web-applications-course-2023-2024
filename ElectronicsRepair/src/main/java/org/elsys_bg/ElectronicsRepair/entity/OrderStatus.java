package org.elsys_bg.ElectronicsRepair.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "OrderStatus")
@Data
public class OrderStatus{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String orderStatus;
}
