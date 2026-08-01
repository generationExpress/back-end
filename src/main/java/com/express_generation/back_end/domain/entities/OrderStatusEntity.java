package com.express_generation.back_end.domain.entities;

import com.express_generation.back_end.utils.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders_status_history")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipmentStatus shipmentStatus;

    @CreationTimestamp
    @Column(nullable = false, updatable = false, name = "update_at")
    private LocalDateTime updatedAt;

    private String observations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private OrderEntity order;


}
