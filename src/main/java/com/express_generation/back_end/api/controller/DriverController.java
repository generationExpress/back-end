package com.express_generation.back_end.api.controller;

import com.express_generation.back_end.api.dtos.request.DriverRequest;
import com.express_generation.back_end.api.dtos.response.DriverResponse;
import com.express_generation.back_end.infracture.adstract_service.IDriverService;
import com.express_generation.back_end.utils.enums.SortType;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
@AllArgsConstructor
public class DriverController {

    @Autowired
    private IDriverService driverService;

    @GetMapping
    public ResponseEntity<Page<DriverResponse>> getAll(
            @Validated @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {

        if (sortType == null) sortType = SortType.NONE;

        return ResponseEntity.ok(this.driverService.getAll(page, size, sortType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponse> getByid (
            @PathVariable String id) throws BadRequestException {

        return ResponseEntity.ok(this.driverService.getById(id));
    }

    @PostMapping
    public ResponseEntity<DriverResponse> create(
            @Validated @RequestBody DriverRequest request) throws BadRequestException {

        return  ResponseEntity.ok(this.driverService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverResponse> update(
            @Validated @RequestBody DriverRequest request,
            @PathVariable String id) throws BadRequestException {

        return ResponseEntity.ok(this.driverService.update(id, request));
    }
}
