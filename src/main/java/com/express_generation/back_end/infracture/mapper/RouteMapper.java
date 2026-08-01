package com.express_generation.back_end.infracture.mapper;

import com.express_generation.back_end.api.dtos.request.RouteRequest;
import com.express_generation.back_end.api.dtos.response.RouteResponse;
import com.express_generation.back_end.domain.entities.RouteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {OrderBasicMapper.class})
public interface RouteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "order", ignore = true)
    RouteEntity toEntity(RouteRequest request);

    RouteResponse toResponse(RouteEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "order", ignore = true)
    void updateEntityFromRequest(RouteRequest request, @MappingTarget RouteEntity entity);
}
