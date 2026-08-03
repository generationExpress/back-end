package com.express_generation.back_end.infracture.adstract_service.generic;

import com.express_generation.back_end.api.dtos.response.OrderStatusResponse;
import org.apache.coyote.BadRequestException;

public interface UpdateService<Request, Response, Id> {
    Response update(Id id, Request request) throws BadRequestException;
}