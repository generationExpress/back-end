package com.express_generation.back_end.infracture.mapper;

import com.express_generation.back_end.api.dtos.request.OrderStatusRequest;
import com.express_generation.back_end.api.dtos.response.OrderStatusResponse;
import com.express_generation.back_end.domain.entities.OrderStatusEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {OrderBasicMapper.class})
public interface OrderStatusMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderStatusEntity toEntity(OrderStatusRequest request);

    OrderStatusResponse toResponse(OrderStatusEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "order", ignore = true)
    void updateEntityFromRequest(OrderStatusRequest request, @MappingTarget OrderStatusEntity entity);
}
