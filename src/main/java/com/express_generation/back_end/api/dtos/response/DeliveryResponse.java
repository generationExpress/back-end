package com.express_generation.back_end.api.dtos.response;

import com.express_generation.back_end.api.dtos.response.basicResponse.OrderBasicResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryResponse {
    private String id;
    private LocalDateTime deliveredAt;
    private String receiverName;
    private String deliveryPhoto;
    private OrderBasicResponse order;
}
