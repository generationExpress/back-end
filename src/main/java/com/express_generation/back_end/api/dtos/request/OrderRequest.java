package com.express_generation.back_end.api.dtos.request;

import com.express_generation.back_end.utils.enums.ShipmentStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @NotBlank(message = "Tracking number is required")
    @Size(max = 255, message = "Tracking number must not exceed 255 characters")
    private String trackingNumber;

    @NotNull(message = "Weight is required")
    @DecimalMin(value = "0.01", message = "Weight must be greater than 0")
    private BigDecimal weightKg;

    @NotNull(message = "Shipment status is required")
    private ShipmentStatus status;

    @NotNull(message = "Estimated delivery date is required")
    @Future(message = "Estimated delivery date must be in the future")
    private Date estimatedDeliveryDate;

    @NotNull(message = "Total cost is required")
    @DecimalMin(value = "0.00", message = "Total cost must be greater than or equal to 0")
    private BigDecimal totalCost;

    @NotNull(message = "Driver is required")
    private String driverId;

    @NotNull(message = "Sender is required")
    private ShippingPersonRequest sender;

    @NotNull(message = "Recipient is required")
    private ShippingPersonRequest recipient;
}