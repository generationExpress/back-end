package com.express_generation.back_end.api.dtos.response.basicResponse;

import com.express_generation.back_end.utils.enums.ShipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderBasicResponse {

    private String id;
    private String trackingNumber;
    private ShipmentStatus status;
    private BigDecimal totalCost;
    private BigDecimal weightKg;
    private LocalDateTime requestDate;
    private Date estimatedDeliveryDate;
    private String driverName;
}
