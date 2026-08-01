package com.express_generation.back_end.api.dtos.request;


import com.express_generation.back_end.utils.enums.ShipmentStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusRequest {

    @NotNull(message = "Shipment status is required")
    private ShipmentStatus shipmentStatus;

    @Size(max = 255, message = "Observations must not exceed 255 characters")
    private String observations;

    @NotNull(message = "Order is required")
    private String orderId;
}