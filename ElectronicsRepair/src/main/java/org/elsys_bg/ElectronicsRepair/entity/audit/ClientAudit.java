package org.elsys_bg.ElectronicsRepair.entity.audit;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.entity.Client;

import java.time.LocalDateTime;

@Entity
@Table(name = "ClientAudit")
@Data
@RequiredArgsConstructor
public class ClientAudit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @OneToOne
    @JoinColumn(name = "clientId")
    private Client client;

    private String name;
    private String password;

    @Column(name = "action")
    private char action;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}