package com.express_generation.back_end.infracture.service;

import com.express_generation.back_end.api.dtos.request.DeliveryRequest;
import com.express_generation.back_end.api.dtos.response.DeliveryResponse;
import com.express_generation.back_end.domain.entities.DeliveryEntity;
import com.express_generation.back_end.domain.entities.OrderEntity;
import com.express_generation.back_end.domain.repositories.DeliveryRepository;
import com.express_generation.back_end.domain.repositories.OrderRepository;
import com.express_generation.back_end.infracture.adstract_service.IDeliveryService;
import com.express_generation.back_end.infracture.mapper.DeliveryMapper;
import com.express_generation.back_end.utils.enums.ShipmentStatus;
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
public class DeliverService implements IDeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DeliveryMapper deliveryMapper;


    @Override
    public DeliveryResponse create(DeliveryRequest request) throws BadRequestException {

        Optional<OrderEntity> existingOrder = this.orderRepository.findById(request.getOrderId());

        if (existingOrder.isEmpty()) throw new RuntimeException(ErrorMessages.IdNotFound("order"));

        DeliveryEntity newDelivery = deliveryMapper.toEntity(request);

        newDelivery.setOrder(existingOrder.get());
        OrderEntity setOrder = existingOrder.get();
        setOrder.setStatus(ShipmentStatus.DELIVERED);

        newDelivery.setOrder(setOrder) ;
        
        DeliveryEntity savedDelivery = deliveryRepository.save(newDelivery);

        return this.deliveryMapper.toResponse(savedDelivery);
    }

    @Override
    public Page<DeliveryResponse> getAll(int page, int size, SortType sortType) {

        if(page < 0)
            page = 0;
        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }
        return this.deliveryRepository.findAll(pagination).map(deliveryMapper::toResponse);
    }

    @Override
    public DeliveryResponse getById(String id) throws BadRequestException {

        DeliveryEntity deliveryEntity = this.find(id);

        return this.deliveryMapper.toResponse(deliveryEntity);
    }

    private DeliveryEntity find(String id) throws BadRequestException {
        return this.deliveryRepository.findById(id).orElseThrow(
                () -> new BadRequestException(ErrorMessages.IdNotFound("delivery")));
    }


}
