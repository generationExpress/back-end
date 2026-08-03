package com.express_generation.back_end.infracture.adstract_service;

import com.express_generation.back_end.api.dtos.request.DeliveryRequest;
import com.express_generation.back_end.api.dtos.response.DeliveryResponse;
import com.express_generation.back_end.infracture.adstract_service.generic.CreateService;
import com.express_generation.back_end.infracture.adstract_service.generic.ReadAllService;
import com.express_generation.back_end.infracture.adstract_service.generic.ReadByIdService;

public interface IDeliveryService extends ReadAllService<DeliveryResponse>,
        ReadByIdService<DeliveryResponse, String>,
        CreateService<DeliveryRequest, DeliveryResponse> {

    static final String FIELD_BY_SORT = "receiverName";
}
