package com.express_generation.back_end.api.controller;

import com.express_generation.back_end.api.dtos.request.OrderStatusRequest;
import com.express_generation.back_end.api.dtos.response.OrderStatusResponse;
import com.express_generation.back_end.infracture.adstract_service.IOrderStatusService;
import com.express_generation.back_end.utils.enums.SortType;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderStatus")
@AllArgsConstructor
public class OrderStatusCotroller {

    @Autowired
    private IOrderStatusService orderStatusService;

    @GetMapping
    public ResponseEntity<Page<OrderStatusResponse>> getAll(
            @Validated @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {

        if (sortType == null) sortType = SortType.NONE;

        return ResponseEntity.ok(orderStatusService.getAll(page, size, sortType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderStatusResponse> getById(@PathVariable String id) throws BadRequestException {

        return ResponseEntity.ok(this.orderStatusService.getById(id));
    }

    @PostMapping
    public ResponseEntity<OrderStatusResponse> create(
            @Validated @RequestBody OrderStatusRequest request) throws BadRequestException {

        return ResponseEntity.ok(this.orderStatusService.create(request));
    }
}
