package com.express_generation.back_end.api.dtos.response;

import com.express_generation.back_end.utils.enums.ShipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private String id;
    private String trackingNumber;
    private BigDecimal weightKg;
    private ShipmentStatus status;
    private LocalDateTime requestDate;
    private LocalDateTime assignedDate;
    private Date estimatedDeliveryDate;
    private BigDecimal totalCost;

    private DriverResponse driver;
    private String driverName;
    private ShippingPersonResponse sender;
    private ShippingPersonResponse recipient;

    private List<OrderStatusResponse> statusHistory;

    private RouteResponse route;

    private DeliveryResponse delivery;
}
