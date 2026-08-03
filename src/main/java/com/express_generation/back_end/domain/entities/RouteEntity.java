package com.express_generation.back_end.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "routes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    /**
     * Tiempo estimado en minutos
     */
    @Column(nullable = false)
    private Integer estimatedTimeMinutes;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "route")
    private List<OrderEntity> orders;
}
