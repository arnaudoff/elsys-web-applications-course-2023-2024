package org.elsys_bg.ElectronicsRepair.entity.audit;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.entity.Worker;

import java.time.LocalDateTime;

@Entity
@Table(name = "WorkerAudit")
@Data
@RequiredArgsConstructor
public class WorkerAudit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @OneToOne
    @JoinColumn(name = "workerId")
    private Worker worker;

    private String name;
    private String password;

    @Column(name = "action")
    private char action;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}