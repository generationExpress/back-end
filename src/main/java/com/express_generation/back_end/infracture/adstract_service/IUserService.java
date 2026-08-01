package com.express_generation.back_end.infracture.adstract_service;

import com.express_generation.back_end.api.dtos.request.Update.UpdateUserRequest;
import com.express_generation.back_end.api.dtos.request.UserRequest;
import com.express_generation.back_end.api.dtos.response.UserResponse;
import com.express_generation.back_end.infracture.adstract_service.generic.CreateService;
import com.express_generation.back_end.infracture.adstract_service.generic.ReadAllService;
import com.express_generation.back_end.infracture.adstract_service.generic.ReadByIdService;
import com.express_generation.back_end.infracture.adstract_service.generic.UpdateService;

public interface IUserService extends ReadAllService<UserResponse>,
        CreateService<UserRequest, UserResponse>,
        ReadByIdService<UserResponse, String>,
        UpdateService<UpdateUserRequest, UserResponse, String> {
    static final String FIELD_BY_SORT = "name";
}
