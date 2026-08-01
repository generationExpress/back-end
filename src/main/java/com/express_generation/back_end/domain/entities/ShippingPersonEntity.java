package com.express_generation.back_end.domain.entities;

import com.express_generation.back_end.utils.enums.ShippingPersonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shipping_person")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShippingPersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(name = "document_number", nullable = false, length = 30)
    private String documentNumber;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(nullable = false, length = 50)
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShippingPersonType shippingPersonType;
}
