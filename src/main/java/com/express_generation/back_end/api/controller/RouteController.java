package com.express_generation.back_end.api.controller;

import com.express_generation.back_end.api.dtos.request.RouteRequest;
import com.express_generation.back_end.api.dtos.response.RouteResponse;
import com.express_generation.back_end.infracture.adstract_service.IRouteService;
import com.express_generation.back_end.utils.enums.SortType;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/route")
@AllArgsConstructor
public class RouteController {

    @Autowired
    private IRouteService routeService;

    @GetMapping
    public ResponseEntity<Page<RouteResponse>> getAll(
            @Validated @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {

        if (sortType == null) sortType = SortType.NONE;

        return ResponseEntity.ok(this.routeService.getAll(page, size, sortType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteResponse> getById(
            @PathVariable String id) throws BadRequestException {

        return ResponseEntity.ok(this.routeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<RouteResponse> create(
            @Validated @RequestBody RouteRequest routeRequest) throws BadRequestException {

        return ResponseEntity.ok(this.routeService.create(routeRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RouteResponse> update(
            @PathVariable String id,
            @Validated @RequestBody RouteRequest routeRequest) throws BadRequestException {

        return ResponseEntity.ok(this.routeService.update(id, routeRequest));
    }

}
