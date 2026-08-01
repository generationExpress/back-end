package com.express_generation.back_end.infracture.service;

import com.express_generation.back_end.api.dtos.request.DriverRequest;
import com.express_generation.back_end.api.dtos.response.DriverResponse;
import com.express_generation.back_end.domain.entities.DriverEntity;
import com.express_generation.back_end.domain.entities.UserEntity;
import com.express_generation.back_end.domain.repositories.DriverRepository;
import com.express_generation.back_end.domain.repositories.UserRepository;
import com.express_generation.back_end.infracture.adstract_service.IDriverService;
import com.express_generation.back_end.infracture.mapper.DriverMapper;
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
public class DriverService implements IDriverService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private DriverMapper driverMapper;

    @Override
    public DriverResponse create(DriverRequest request) throws BadRequestException {

        //Validamos usuario asociado
        Optional<UserEntity> existingUser = this.userRepository.findById(request.getUserId());

        //si no existe lazamos exepcion
        if(existingUser.isEmpty()) throw new BadRequestException(ErrorMessages.NotFound(request.getUserId()));

        //Covertimos el dto request a entidad
        DriverEntity newDriverEntity = driverMapper.toEntity(request);
        //agregamos el usuario
        newDriverEntity.setUser(existingUser.get());
        //guardamos
        DriverEntity savedDriverEntity = this.driverRepository.save(newDriverEntity);
        //retornamos le dto de respuesta
        return this.driverMapper.toResponse(savedDriverEntity);
    }

    @Override
    public Page<DriverResponse> getAll(int page, int size, SortType sortType) {
        if(page < 0)
            page = 0;
        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.driverRepository.findAll(pagination).map(driverMapper::toResponse);

    }

    @Override
    public DriverResponse getById(String id) throws BadRequestException {

        DriverEntity driverEntity = this.find(id);

        return this.driverMapper.toResponse(driverEntity);
    }

    @Override
    public DriverResponse update(String id, DriverRequest driverRequest) throws BadRequestException {

        DriverEntity driverEntity = this.find(id);

        this.driverMapper.updateEntityFromRequest(driverRequest,driverEntity);
        driverEntity = this.driverRepository.save(driverEntity);

        return this.driverMapper.toResponse(driverEntity);
    }

    private DriverEntity find(String id) throws BadRequestException {

        return this.driverRepository.findById(id).orElseThrow(()
                -> new BadRequestException(ErrorMessages.NotFound(id)));
    }
}
