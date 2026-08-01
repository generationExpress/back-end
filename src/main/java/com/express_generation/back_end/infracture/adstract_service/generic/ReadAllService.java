package com.express_generation.back_end.infracture.adstract_service.generic;

import com.express_generation.back_end.utils.enums.SortType;
import org.springframework.data.domain.Page;

public interface  ReadAllService<Response> {
    Page<Response> getAll(int page, int size, SortType sortType);
}