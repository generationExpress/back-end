package com.express_generation.back_end.api.controller;


import com.express_generation.back_end.api.dtos.request.OrderRequest;
import com.express_generation.back_end.api.dtos.response.OrderResponse;
import com.express_generation.back_end.infracture.adstract_service.IOrderService;
import com.express_generation.back_end.utils.enums.SortType;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping
    public ResponseEntity<Page<OrderResponse>> getAll(
            @Validated @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType){

        if (sortType == null) sortType = SortType.NONE;

        return ResponseEntity.ok(orderService.getAll(page, size, sortType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable String id) throws BadRequestException {

        return ResponseEntity.ok(this.orderService.getById(id));
    }

    @GetMapping("/rastreo/{orderNumber}")
    public ResponseEntity<OrderResponse> getOrderNumber(@PathVariable String orderNumber) throws BadRequestException {

        return ResponseEntity.ok(this.orderService.findByOrderNumber(orderNumber));
    }


    @GetMapping("/route/{routeId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByRouteId(
            @PathVariable String routeId) throws BadRequestException {

        return ResponseEntity.ok(this.orderService.getOrdersByRouteId(routeId));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(
            @Validated @RequestBody OrderRequest request) throws BadRequestException {

        return ResponseEntity.ok(this.orderService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(
            @Validated @RequestBody OrderRequest request,
            @PathVariable String id) throws BadRequestException {

        return ResponseEntity.ok(this.orderService.update(id, request));
    }
}
