package org.elsys_bg.ElectronicsRepair.entity.audit;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.entity.Admin;
import org.elsys_bg.ElectronicsRepair.entity.Client;
import org.elsys_bg.ElectronicsRepair.entity.ClientContact;

import java.time.LocalDateTime;

@Entity
@Table(name = "ClientContactAudit")
@Data
@RequiredArgsConstructor
public class ClientContactAudit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @OneToOne
    @JoinColumn(name = "clientContactId")
    private ClientContact clientContact;

    @OneToOne
    @JoinColumn(name = "clientId")
    private Client client;

    private String email;
    private String tel;

    @Column(name = "action")
    private char action;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
