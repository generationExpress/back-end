package com.express_generation.back_end.api.dtos.response;

import com.express_generation.back_end.utils.enums.ShippingPersonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShippingPersonResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String email;
    private String phone;
    private String address;
    private String city;
    private ShippingPersonType shippingPersonType;
}
