package org.elsys_bg.ElectronicsRepair.entity;

import lombok.Data;
import jakarta.persistence.*;
import org.springframework.data.domain.Auditable;

@Entity
@Table(name = "Reviews")
@Data
public class Review{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    private String reviewText;
}