package com.express_generation.back_end.infracture.adstract_service.generic;


import org.apache.coyote.BadRequestException;

public interface ReadByIdService<Response, Id> {
    Response getById(Id id) throws BadRequestException;
}
