package com.express_generation.back_end.infracture.mapper;

import com.express_generation.back_end.api.dtos.request.VehicleRequest;
import com.express_generation.back_end.api.dtos.response.VehicleResponse;
import com.express_generation.back_end.domain.entities.VehicleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {DriverMapper.class})
public interface VehicleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "driver", ignore = true)
    VehicleEntity toEntity(VehicleRequest request);

    VehicleResponse toResponse(VehicleEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "driver", ignore = true)
    void updateEntityFromRequest(VehicleRequest request, @MappingTarget VehicleEntity entity);
}
