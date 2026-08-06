package com.express_generation.back_end.infracture.adstract_service;

import com.express_generation.back_end.api.dtos.request.OrderRequest;
import com.express_generation.back_end.api.dtos.response.OrderResponse;
import com.express_generation.back_end.domain.entities.OrderEntity;
import com.express_generation.back_end.infracture.adstract_service.generic.CreateService;
import com.express_generation.back_end.infracture.adstract_service.generic.ReadAllService;
import com.express_generation.back_end.infracture.adstract_service.generic.ReadByIdService;
import com.express_generation.back_end.infracture.adstract_service.generic.UpdateService;

import org.apache.coyote.BadRequestException;
import java.util.List;

public interface IOrderService extends ReadAllService<OrderResponse>,
        ReadByIdService<OrderResponse, String>,
        CreateService<OrderRequest, OrderResponse>,
        UpdateService<OrderRequest, OrderResponse, String> {

    static final String FIELD_BY_SORT = "requestDate";

    List<OrderResponse> getOrdersByRouteId(String routeId) throws BadRequestException;

    OrderResponse findByOrderNumber(String orderNumber);
}
