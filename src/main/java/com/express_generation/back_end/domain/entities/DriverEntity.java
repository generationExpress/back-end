package com.express_generation.back_end.domain.entities;

import com.express_generation.back_end.utils.enums.License;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "drivers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private License license;

    @Column(nullable = false)
    private boolean available;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @OneToMany(mappedBy = "driver", fetch = FetchType.EAGER,  cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<OrderEntity> orders;

}
