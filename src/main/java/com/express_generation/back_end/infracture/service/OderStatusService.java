package com.express_generation.back_end.infracture.service;

import com.express_generation.back_end.api.dtos.request.OrderRequest;
import com.express_generation.back_end.api.dtos.request.OrderStatusRequest;
import com.express_generation.back_end.api.dtos.response.OrderStatusResponse;
import com.express_generation.back_end.domain.entities.OrderEntity;
import com.express_generation.back_end.domain.entities.OrderStatusEntity;
import com.express_generation.back_end.domain.repositories.OrderRepository;
import com.express_generation.back_end.domain.repositories.OrderStatusRepository;
import com.express_generation.back_end.infracture.adstract_service.IOrderStatusService;
import com.express_generation.back_end.infracture.mapper.OrderStatusMapper;
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
public class OderStatusService implements IOrderStatusService {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Override
    public OrderStatusResponse create(OrderStatusRequest request) throws BadRequestException {

        Optional<OrderEntity> existingOrderEntity = this.orderRepository.findById(request.getOrderId());

        if (existingOrderEntity.isEmpty()) throw  new BadRequestException(ErrorMessages.IdNotFound(request.getOrderId()));

        OrderStatusEntity newOrderStatusEntity = orderStatusMapper.toEntity(request);
        newOrderStatusEntity.setOrder(existingOrderEntity.get());
        OrderStatusEntity savedOrderStatus = this.orderStatusRepository.save(newOrderStatusEntity);

        return this.orderStatusMapper.toResponse(savedOrderStatus);
    }

    @Override
    public Page<OrderStatusResponse> getAll(int page, int size, SortType sortType) {
        if(page < 0)
            page = 0;
        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.orderStatusRepository.findAll(pagination).map(orderStatusMapper::toResponse);
    }

    @Override
    public OrderStatusResponse getById(String id) throws BadRequestException {

        OrderStatusEntity orderStatusEntity = this.find(id);

        return this.orderStatusMapper.toResponse(orderStatusEntity);
    }


    private OrderStatusEntity find(String id) throws BadRequestException {

        return this.orderStatusRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.IdNotFound(id)));
    }
}
