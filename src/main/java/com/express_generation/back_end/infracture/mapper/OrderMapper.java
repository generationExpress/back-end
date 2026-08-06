package com.express_generation.back_end.infracture.mapper;

import com.express_generation.back_end.api.dtos.request.OrderRequest;
import com.express_generation.back_end.api.dtos.response.OrderResponse;
import com.express_generation.back_end.api.dtos.response.basicResponse.OrderBasicResponse;
import com.express_generation.back_end.domain.entities.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {
        DriverMapper.class,
        ShippingPersonMapper.class,
        OrderBasicMapper.class,
        OrderStatusMapper.class,
        RouteMapper.class,
        DeliveryMapper.class
})
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "requestDate", ignore = true)
    @Mapping(target = "assignedDate", ignore = true)
    @Mapping(target = "driver", ignore = true)
    @Mapping(target = "sender", ignore = true)
    @Mapping(target = "recipient", ignore = true)
    @Mapping(target = "statusHistory", ignore = true)
    @Mapping(target = "route", ignore = true)
    @Mapping(target = "delivery", ignore = true)
    OrderEntity toEntity(OrderRequest request);

    @Mapping(target = "driverName", expression = "java(entity.getDriver() != null && entity.getDriver().getUser() != null ? entity.getDriver().getUser().getFirstName() + \" \" + entity.getDriver().getUser().getLastName() : null)")
    OrderResponse toResponse(OrderEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "requestDate", ignore = true)
    @Mapping(target = "assignedDate", ignore = true)
    @Mapping(target = "driver", ignore = true)
    @Mapping(target = "sender", ignore = true)
    @Mapping(target = "recipient", ignore = true)
    @Mapping(target = "statusHistory", ignore = true)
    @Mapping(target = "route", ignore = true)
    @Mapping(target = "delivery", ignore = true)
    void updateEntityFromRequest(OrderRequest request, @MappingTarget OrderEntity entity);
}
