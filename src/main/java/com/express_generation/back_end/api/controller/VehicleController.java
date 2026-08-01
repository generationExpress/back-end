package com.express_generation.back_end.api.controller;

import com.express_generation.back_end.api.dtos.request.VehicleRequest;
import com.express_generation.back_end.api.dtos.response.VehicleResponse;
import com.express_generation.back_end.infracture.adstract_service.IVehicleService;
import com.express_generation.back_end.utils.enums.SortType;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
@AllArgsConstructor
public class VehicleController {

    @Autowired
    private IVehicleService vehicleService;

    @GetMapping
    public ResponseEntity<Page<VehicleResponse>> getAllVehicles(
            @Validated @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {

        if (sortType == null) sortType = SortType.NONE;

        return ResponseEntity.ok(this.vehicleService.getAll(page, size, sortType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getById(
            @PathVariable String id) throws BadRequestException {

        return ResponseEntity.ok(this.vehicleService.getById(id));
    }

    @PostMapping
    public ResponseEntity<VehicleResponse> create(
            @Validated @RequestBody VehicleRequest request) throws BadRequestException {

        return ResponseEntity.ok(this.vehicleService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponse> update(
            @Validated @RequestBody VehicleRequest request,
            @PathVariable String id) throws BadRequestException {

        return ResponseEntity.ok(this.vehicleService.update(id, request));
    }
}
