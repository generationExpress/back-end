package com.express_generation.back_end.infracture.mapper;

import com.express_generation.back_end.api.dtos.response.basicResponse.OrderBasicResponse;
import com.express_generation.back_end.domain.entities.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderBasicMapper {

    @Mapping(target = "driverName", expression = "java(entity.getDriver() != null && entity.getDriver().getUser() != null ? entity.getDriver().getUser().getFirstName() + \" \" + entity.getDriver().getUser().getLastName() : null)")
    OrderBasicResponse toBasicResponse(OrderEntity entity);
}
