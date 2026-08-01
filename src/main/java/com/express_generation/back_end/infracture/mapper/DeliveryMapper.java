package com.express_generation.back_end.infracture.mapper;

import com.express_generation.back_end.api.dtos.request.DeliveryRequest;
import com.express_generation.back_end.api.dtos.response.DeliveryResponse;
import com.express_generation.back_end.domain.entities.DeliveryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {OrderBasicMapper.class})
public interface DeliveryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deliveredAt", ignore = true)
    @Mapping(target = "order", ignore = true)
    DeliveryEntity toEntity(DeliveryRequest request);

    DeliveryResponse toResponse(DeliveryEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deliveredAt", ignore = true)
    @Mapping(target = "order", ignore = true)
    void updateEntityFromRequest(DeliveryRequest request, @MappingTarget DeliveryEntity entity);
}
