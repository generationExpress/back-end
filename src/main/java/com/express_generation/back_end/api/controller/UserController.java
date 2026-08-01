package com.express_generation.back_end.api.controller;


import com.express_generation.back_end.api.dtos.request.Update.UpdateUserRequest;
import com.express_generation.back_end.api.dtos.request.UserRequest;
import com.express_generation.back_end.api.dtos.response.UserResponse;
import com.express_generation.back_end.infracture.adstract_service.IUserService;
import com.express_generation.back_end.utils.enums.SortType;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAll (
            @Validated @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false)SortType sortType){

        if (sortType == null) sortType = SortType.NONE;

        return ResponseEntity.ok(this.userService.getAll(page, size, sortType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(
            @PathVariable String id) throws BadRequestException {

        return ResponseEntity.ok(this.userService.getById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(
            @Validated @RequestBody UserRequest request) throws BadRequestException {


        return ResponseEntity.ok(this.userService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @Validated @RequestBody UpdateUserRequest request,
            @PathVariable String id) throws BadRequestException {

        return ResponseEntity.ok(this.userService.update(id, request));
    }

}
