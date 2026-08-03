package com.express_generation.back_end.infracture.adstract_service;

import com.express_generation.back_end.api.dtos.request.OrderRequest;
import com.express_generation.back_end.api.dtos.request.OrderStatusRequest;
import com.express_generation.back_end.api.dtos.response.OrderResponse;
import com.express_generation.back_end.api.dtos.response.OrderStatusResponse;
import com.express_generation.back_end.infracture.adstract_service.generic.CreateService;
import com.express_generation.back_end.infracture.adstract_service.generic.ReadAllService;
import com.express_generation.back_end.infracture.adstract_service.generic.ReadByIdService;
import com.express_generation.back_end.infracture.adstract_service.generic.UpdateService;

public interface IOrderStatusService extends CreateService<OrderStatusRequest, OrderStatusResponse>,
        ReadAllService<OrderStatusResponse>,
        ReadByIdService<OrderStatusResponse, String>{

    static final String FIELD_BY_SORT = "shipmentStatus";
}
