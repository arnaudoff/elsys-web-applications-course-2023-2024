package org.elsys_bg.ElectronicsRepair.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "ClientContacts")
@Data
public class ClientContact{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "clientId")
    private Client client;

    private String email;

    private String tel;
}