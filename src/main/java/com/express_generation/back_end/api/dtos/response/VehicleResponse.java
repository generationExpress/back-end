package com.express_generation.back_end.api.dtos.response;

import com.express_generation.back_end.utils.enums.VehicleStatus;
import com.express_generation.back_end.utils.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponse {

    private String id;
    private String licensePlate;
    private VehicleType type;
    private String brand;
    private BigDecimal capacityKg;
    private VehicleStatus status;
    private DriverResponse driver;
}
