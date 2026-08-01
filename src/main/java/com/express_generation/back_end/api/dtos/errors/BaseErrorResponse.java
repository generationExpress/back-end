package com.express_generation.back_end.api.dtos.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseErrorResponse implements Serializable {
    private Integer code;
    private String status;
}