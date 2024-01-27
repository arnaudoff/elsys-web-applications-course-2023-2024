package org.elsys_bg.ElectronicsRepair.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "WorkerPosts")
@Data
public class WorkerPost{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String post;
}