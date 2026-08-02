package com.express_generation.back_end.infracture.service;

import com.express_generation.back_end.api.dtos.request.VehicleRequest;
import com.express_generation.back_end.api.dtos.response.VehicleResponse;
import com.express_generation.back_end.domain.entities.DriverEntity;
import com.express_generation.back_end.domain.entities.VehicleEntity;
import com.express_generation.back_end.domain.repositories.DriverRepository;
import com.express_generation.back_end.domain.repositories.VehicleRepository;
import com.express_generation.back_end.infracture.adstract_service.IVehicleService;
import com.express_generation.back_end.infracture.mapper.VehicleMapper;
import com.express_generation.back_end.utils.enums.SortType;
import com.express_generation.back_end.utils.exeption.ErrorMessages;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VehicleService implements IVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleMapper vehicleMapper;
    @Autowired
    private DriverRepository driverRepository;

    @Override
    public VehicleResponse create(VehicleRequest request) throws BadRequestException {

        Optional<VehicleEntity> existingVehicle = vehicleRepository.findById(request.getLicensePlate());
        if (existingVehicle.isPresent()) throw  new BadRequestException(ErrorMessages.alreadyExists(request.getLicensePlate()));

        DriverEntity driverEntity = driverRepository.findById(request.getDriverId())
                .orElseThrow(() -> new BadRequestException(ErrorMessages.IdNotFound("Driver")));

        VehicleEntity vehicleEntity = this.vehicleMapper.toEntity(request);
        vehicleEntity.setDriver(driverEntity);
        VehicleEntity newVehicleEntity = vehicleRepository.save(vehicleEntity);

        return this.vehicleMapper.toResponse(newVehicleEntity);
    }

    @Override
    public Page<VehicleResponse> getAll(int page, int size, SortType sortType) {

        if(page < 0)
            page = 0;
        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }
        return this.vehicleRepository.findAll(pagination).map(vehicleMapper::toResponse);
    }

    @Override
    public VehicleResponse getById(String id) throws BadRequestException {

        VehicleEntity vehicleEntity = find(id);

        return this.vehicleMapper.toResponse(vehicleEntity);
    }

    @Override
    public VehicleResponse update(String id, VehicleRequest updateUserRequest) throws BadRequestException {

        VehicleEntity vehicleEntity = find(id);

        com.express_generation.back_end.domain.entities.DriverEntity driverEntity = driverRepository.findById(updateUserRequest.getDriverId())
                .orElseThrow(() -> new BadRequestException(ErrorMessages.IdNotFound("Driver")));

        this.vehicleMapper.updateEntityFromRequest(updateUserRequest, vehicleEntity);
        vehicleEntity.setDriver(driverEntity);
        VehicleEntity vehicleSaved = vehicleRepository.save(vehicleEntity);

        return this.vehicleMapper.toResponse(vehicleSaved);
    }

    private VehicleEntity find(String id) throws BadRequestException {

        return this.vehicleRepository.findById(id).orElseThrow(
                () -> new BadRequestException(ErrorMessages.IdNotFound("Vehicle")));
    }
}
