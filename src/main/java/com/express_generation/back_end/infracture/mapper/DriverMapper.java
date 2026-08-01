package com.express_generation.back_end.infracture.mapper;

import com.express_generation.back_end.api.dtos.request.DriverRequest;
import com.express_generation.back_end.api.dtos.response.DriverResponse;
import com.express_generation.back_end.domain.entities.DriverEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface DriverMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "orders", ignore = true)
    DriverEntity toEntity(DriverRequest request);

    DriverResponse toResponse(DriverEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "orders", ignore = true)
    void updateEntityFromRequest(DriverRequest request, @MappingTarget DriverEntity entity);
}
