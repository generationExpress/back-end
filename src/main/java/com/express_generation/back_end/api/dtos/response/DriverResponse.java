package com.express_generation.back_end.api.dtos.response;

import com.express_generation.back_end.utils.enums.License;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverResponse {

    private String id;
    private License license;
    private boolean available;
    private UserResponse user;
}
