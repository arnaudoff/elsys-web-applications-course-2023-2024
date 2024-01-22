package com.dreamix.overachievers.entity;

import com.dreamix.overachievers.vo.ReviewType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "review")
@Table(name = "review", schema = "postgres")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Employee receiver;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Employee sender;

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    private LocalDateTime lastUpdatedAt;

    @Column
    private String videoUrl;

    @Enumerated(EnumType.STRING)
    private ReviewType type;

}