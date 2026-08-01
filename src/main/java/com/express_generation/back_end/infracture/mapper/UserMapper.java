package com.express_generation.back_end.infracture.mapper;

import com.express_generation.back_end.api.dtos.request.Update.UpdateUserRequest;
import com.express_generation.back_end.api.dtos.request.UserRequest;
import com.express_generation.back_end.api.dtos.response.UserResponse;
import com.express_generation.back_end.domain.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    UserEntity toEntity(UserRequest request);

    UserResponse toResponse(UserEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateEntityFromRequest(UpdateUserRequest request, @MappingTarget UserEntity entity);
}
