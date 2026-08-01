package com.express_generation.back_end.infracture.adstract_service;

import com.express_generation.back_end.api.dtos.request.VehicleRequest;
import com.express_generation.back_end.api.dtos.response.VehicleResponse;
import com.express_generation.back_end.infracture.adstract_service.generic.CreateService;
import com.express_generation.back_end.infracture.adstract_service.generic.ReadAllService;
import com.express_generation.back_end.infracture.adstract_service.generic.ReadByIdService;
import com.express_generation.back_end.infracture.adstract_service.generic.UpdateService;

public interface IVehicleService extends ReadAllService<VehicleResponse>,
        ReadByIdService<VehicleResponse, String>,
        UpdateService<VehicleRequest, VehicleResponse, String>,
        CreateService<VehicleRequest ,VehicleResponse> {
    static final String FIELD_BY_SORT = "capacityKg";
}
