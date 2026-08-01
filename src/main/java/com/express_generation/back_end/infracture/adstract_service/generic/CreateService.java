package com.express_generation.back_end.infracture.adstract_service.generic;

import org.apache.coyote.BadRequestException;

public interface CreateService <Requets, Response> {

    Response create(Requets request) throws BadRequestException;

}
