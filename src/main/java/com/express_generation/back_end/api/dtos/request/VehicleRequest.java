package com.express_generation.back_end.api.dtos.request;


import com.express_generation.back_end.utils.enums.VehicleStatus;
import com.express_generation.back_end.utils.enums.VehicleType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {

    @NotBlank(message = "License plate is required")
    @Size(min = 6, max = 6, message = "License plate must have exactly 6 characters")
    private String licensePlate;

    @NotNull(message = "Vehicle type is required")
    private VehicleType type;

    @NotBlank(message = "Brand is required")
    @Size(max = 50, message = "Brand must not exceed 50 characters")
    private String brand;

    @NotNull(message = "Capacity is required")
    @DecimalMin(value = "0.01", message = "Capacity must be greater than 0")
    private BigDecimal capacityKg;

    @NotNull(message = "Vehicle status is required")
    private VehicleStatus status;

    @NotNull(message = "Driver ID is required")
    private String driverId;
}