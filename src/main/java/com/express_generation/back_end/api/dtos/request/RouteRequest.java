package com.express_generation.back_end.api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteRequest {

    @NotBlank(message = "Origin is required")
    @Size(max = 255, message = "Origin must not exceed 255 characters")
    private String origin;

    @NotBlank(message = "Destination is required")
    @Size(max = 255, message = "Destination must not exceed 255 characters")
    private String destination;

    @NotNull(message = "Estimated time is required")
    @Positive(message = "Estimated time must be greater than 0")
    private Integer estimatedTimeMinutes;

    @NotNull(message = "Order IDs list is required")
    private List<String> orderIds;
}