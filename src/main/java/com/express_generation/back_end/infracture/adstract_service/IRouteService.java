package com.express_generation.back_end.infracture.adstract_service;

import com.express_generation.back_end.api.dtos.request.RouteRequest;
import com.express_generation.back_end.api.dtos.response.RouteResponse;
import com.express_generation.back_end.infracture.adstract_service.generic.CreateService;
import com.express_generation.back_end.infracture.adstract_service.generic.ReadAllService;
import com.express_generation.back_end.infracture.adstract_service.generic.ReadByIdService;
import com.express_generation.back_end.infracture.adstract_service.generic.UpdateService;


public interface IRouteService extends ReadAllService<RouteResponse>,
        ReadByIdService<RouteResponse, String>,
        UpdateService< RouteRequest, RouteResponse, String>,
        CreateService<RouteRequest, RouteResponse> {

        static final String FIELD_BY_SORT = "origin";
}
