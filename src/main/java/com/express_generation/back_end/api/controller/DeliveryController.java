package com.express_generation.back_end.api.controller;

import com.express_generation.back_end.api.dtos.request.DeliveryRequest;
import com.express_generation.back_end.api.dtos.response.DeliveryResponse;
import com.express_generation.back_end.infracture.adstract_service.IDeliveryService;
import com.express_generation.back_end.utils.enums.SortType;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
@AllArgsConstructor
public class DeliveryController {

    @Autowired
    private IDeliveryService deliveryService;

    @GetMapping
    public ResponseEntity<Page<DeliveryResponse>> getAll(
            @Validated @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {

        if (sortType == null) sortType = SortType.NONE;

        return ResponseEntity.ok(this.deliveryService.getAll(page, size, sortType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryResponse> getById(
            @PathVariable String id) throws BadRequestException {

        return ResponseEntity.ok(this.deliveryService.getById(id));
    }

    @PostMapping
    public ResponseEntity<DeliveryResponse> create(
            @Validated @RequestBody DeliveryRequest request) throws BadRequestException {

        return ResponseEntity.ok(this.deliveryService.create(request));
    }
}
