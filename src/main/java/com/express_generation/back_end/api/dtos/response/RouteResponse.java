package com.express_generation.back_end.api.dtos.response;

import com.express_generation.back_end.api.dtos.response.basicResponse.OrderBasicResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteResponse {

    private String id;
    private String origin;
    private String destination;
    private Integer estimatedTimeMinutes;
    private LocalDateTime createdAt;
    private List<OrderBasicResponse> orders;
}
