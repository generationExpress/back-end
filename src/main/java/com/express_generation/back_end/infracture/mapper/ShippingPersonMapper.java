package com.express_generation.back_end.infracture.mapper;

import com.express_generation.back_end.api.dtos.request.ShippingPersonRequest;
import com.express_generation.back_end.api.dtos.response.ShippingPersonResponse;
import com.express_generation.back_end.domain.entities.ShippingPersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShippingPersonMapper {

    @Mapping(target = "id", ignore = true)
    ShippingPersonEntity toEntity(ShippingPersonRequest request);

    ShippingPersonResponse toResponse(ShippingPersonEntity entity);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(ShippingPersonRequest request, @MappingTarget ShippingPersonEntity entity);
}
