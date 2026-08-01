package com.express_generation.back_end.infracture.adstract_service.generic;

import org.apache.coyote.BadRequestException;

public interface UpdateService<Request, Response, Id> {
    Response update(Id id, Request request) throws BadRequestException;
}