package com.express_generation.back_end.infracture.service;

import com.express_generation.back_end.api.dtos.request.Update.UpdateUserRequest;
import com.express_generation.back_end.api.dtos.request.UserRequest;
import com.express_generation.back_end.api.dtos.response.UserResponse;
import com.express_generation.back_end.domain.entities.UserEntity;
import com.express_generation.back_end.domain.repositories.UserRepository;
import com.express_generation.back_end.infracture.adstract_service.IUserService;
import com.express_generation.back_end.infracture.mapper.UserMapper;
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
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<UserResponse> getAll(int page, int size, SortType sortType) {

        if(page < 0)
            page = 0;
        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.userRepository.findAll(pagination).map(userMapper::toResponse);
    }


    @Override
    public UserResponse create(UserRequest request) throws BadRequestException {

        //Validamos si ya existe un email
        Optional<UserEntity> existingEntity = this.userRepository.findByEmail(request.getEmail());


        //Si existe lanzamos la exception
        if(existingEntity.isPresent()) throw new BadRequestException(ErrorMessages.alreadyExists(request.getEmail()));

        UserEntity userEntity = this.userMapper.toEntity(request);
        UserEntity userSaved = this.userRepository.save(userEntity);

        return this.userMapper.toResponse(userSaved);
    }


    @Override
    public UserResponse getById(String id) throws BadRequestException {

        UserEntity userEntity = this.find(id);

        return this.userMapper.toResponse(userEntity);
    }

    @Override
    public UserResponse update(String id, UpdateUserRequest userRequest) throws BadRequestException {

        UserEntity userEntity = this.find(id);

        this.userMapper.updateEntityFromRequest(userRequest, userEntity);
        UserEntity userSaved = this.userRepository.save(userEntity);

        return this.userMapper.toResponse(userSaved);
    }

    private UserEntity find(String id) throws BadRequestException {

        return this.userRepository.findById(id).orElseThrow(()
                -> new BadRequestException(ErrorMessages.IdNotFound("User")));
    }
}
