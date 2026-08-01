package com.express_generation.back_end.infracture.adstract_service;

import com.express_generation.back_end.api.dtos.request.DriverRequest;
import com.express_generation.back_end.api.dtos.response.DriverResponse;
import com.express_generation.back_end.infracture.adstract_service.generic.CreateService;
import com.express_generation.back_end.infracture.adstract_service.generic.ReadAllService;
import com.express_generation.back_end.infracture.adstract_service.generic.ReadByIdService;
import com.express_generation.back_end.infracture.adstract_service.generic.UpdateService;

public interface IDriverService extends ReadAllService<DriverResponse>,
        CreateService<DriverRequest, DriverResponse>,
        ReadByIdService<DriverResponse, String>, UpdateService<DriverRequest, DriverResponse, String> {

    static final String FIELD_BY_SORT = "name";
}
