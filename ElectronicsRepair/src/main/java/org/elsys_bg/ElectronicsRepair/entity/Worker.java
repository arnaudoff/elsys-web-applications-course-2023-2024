package org.elsys_bg.ElectronicsRepair.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "Workers")
@Data
public class Worker{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String password;

    @ManyToOne
    @JoinColumn(name = "postId")
    private WorkerPost post;
}