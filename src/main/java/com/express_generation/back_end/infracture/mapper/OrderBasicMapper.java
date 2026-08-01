package com.express_generation.back_end.infracture.mapper;

import com.express_generation.back_end.api.dtos.response.basicResponse.OrderBasicResponse;
import com.express_generation.back_end.domain.entities.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderBasicMapper {

    OrderBasicResponse toBasicResponse(OrderEntity entity);
}
