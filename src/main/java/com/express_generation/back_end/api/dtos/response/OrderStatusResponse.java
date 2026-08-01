package com.express_generation.back_end.api.dtos.response;

import com.express_generation.back_end.api.dtos.response.basicResponse.OrderBasicResponse;
import com.express_generation.back_end.utils.enums.ShipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusResponse {

    private String id;
    private ShipmentStatus shipmentStatus;
    private LocalDateTime updatedAt;
    private String observations;
    private OrderBasicResponse order;
}
