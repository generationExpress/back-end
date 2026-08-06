package com.express_generation.back_end.infracture.service;

import com.express_generation.back_end.api.dtos.request.OrderRequest;
import com.express_generation.back_end.api.dtos.response.OrderResponse;
import com.express_generation.back_end.api.dtos.response.OrderStatusResponse;
import com.express_generation.back_end.domain.entities.DriverEntity;
import com.express_generation.back_end.domain.entities.OrderEntity;
import com.express_generation.back_end.domain.entities.ShippingPersonEntity;
import com.express_generation.back_end.domain.repositories.DriverRepository;
import com.express_generation.back_end.domain.repositories.OrderRepository;
import com.express_generation.back_end.domain.repositories.RouteRepository;
import com.express_generation.back_end.domain.repositories.ShippingPersonRepository;
import com.express_generation.back_end.infracture.adstract_service.IOrderService;
import com.express_generation.back_end.infracture.mapper.OrderMapper;
import com.express_generation.back_end.infracture.mapper.ShippingPersonMapper;
import com.express_generation.back_end.utils.enums.SortType;
import com.express_generation.back_end.utils.exeption.ErrorMessages;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ShippingPersonRepository shippingPersonRepository;
    @Autowired
    private ShippingPersonMapper shippingPersonMapper;

    @Autowired
    private DriverRepository  driverRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Transactional
    @Override
    public OrderResponse create(OrderRequest request) throws BadRequestException {

        if (request.getRecipient().getShippingPersonType() == request.getSender().getShippingPersonType())
            throw new BadRequestException(ErrorMessages.ShippingPersonType());

        Optional<OrderEntity> existingTrackingNumber = this.orderRepository.findBytrackingNumber(request.getTrackingNumber());
        Optional<DriverEntity> existingDrive = this.driverRepository.findById(request.getDriverId());

        Optional<ShippingPersonEntity> existingShippingPersonSender = this.shippingPersonRepository.findBydocumentNumber(
                request.getSender().getDocumentNumber());
        Optional<ShippingPersonEntity> existingShippingRecipient = this.shippingPersonRepository.findBydocumentNumber(
                request.getRecipient().getDocumentNumber());

        if (existingDrive.isEmpty()) throw new BadRequestException(ErrorMessages.IdNotFound("Driver"));
        if (existingTrackingNumber.isPresent()) throw new BadRequestException(ErrorMessages.alreadyExists(request.getTrackingNumber()));

        ShippingPersonEntity senderSaved;
        ShippingPersonEntity recipientSaved;

        if (existingShippingPersonSender.isEmpty()) {
            senderSaved = this.shippingPersonRepository.save(this.shippingPersonMapper.toEntity(request.getSender()));
        }else {
            senderSaved = existingShippingPersonSender.get();
        }

        if(existingShippingRecipient.isEmpty()){
            recipientSaved = this.shippingPersonRepository.save(this.shippingPersonMapper.toEntity(request.getRecipient()));
        } else {
            recipientSaved = existingShippingRecipient.get();
        }

        OrderEntity orderEntity = this.orderMapper.toEntity(request);
        orderEntity.setDriver(existingDrive.get());
        orderEntity.setRecipient(recipientSaved);
        orderEntity.setSender(senderSaved);

        OrderEntity orderSaved = this.orderRepository.save(orderEntity);

        return this.orderMapper.toResponse(orderSaved);
    }

    @Override
    public Page<OrderResponse> getAll(int page, int size, SortType sortType) {
        if(page < 0)
            page = 0;
        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.orderRepository.findAll(pagination).map(orderMapper::toResponse);
    }

    @Override
    public OrderResponse getById(String id) throws BadRequestException {

        OrderEntity orderEntity = this.find(id);

        return this.orderMapper.toResponse(orderEntity);
    }

    @Override
    public OrderResponse update(String id, OrderRequest orderRequest) throws BadRequestException {

        OrderEntity orderEntity = this.find(id);
        this.orderMapper.updateEntityFromRequest(orderRequest, orderEntity);

        return this.orderMapper.toResponse(orderEntity);
    }

    @Override
    public List<OrderResponse> getOrdersByRouteId(String routeId) throws BadRequestException {

        if (!this.routeRepository.existsById(routeId)) {
            throw new BadRequestException(ErrorMessages.IdNotFound("Route"));
        }

        List<OrderEntity> orders = this.orderRepository.findByRouteId(routeId);

        return orders.stream()
                .map(this.orderMapper::toResponse)
                .toList();
    }

    private OrderEntity find(String id) throws BadRequestException {

        return this.orderRepository.findById(id).orElseThrow(() ->
                new BadRequestException(ErrorMessages.IdNotFound("Order")));
    }
}
