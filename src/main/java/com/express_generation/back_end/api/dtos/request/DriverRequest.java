package com.express_generation.back_end.api.dtos.request;


import com.express_generation.back_end.utils.enums.License;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverRequest {

    @NotNull(message = "License is required")
    private License license;

    @NotNull(message = "Availability is required")
    private Boolean available;

    @NotNull(message = "User ID is required")
    private String userId;
}