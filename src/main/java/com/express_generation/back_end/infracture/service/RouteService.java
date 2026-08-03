package com.express_generation.back_end.infracture.service;

import com.express_generation.back_end.api.dtos.request.RouteRequest;
import com.express_generation.back_end.api.dtos.response.RouteResponse;
import com.express_generation.back_end.domain.entities.OrderEntity;
import com.express_generation.back_end.domain.entities.RouteEntity;
import com.express_generation.back_end.domain.repositories.OrderRepository;
import com.express_generation.back_end.domain.repositories.RouteRepository;
import com.express_generation.back_end.infracture.adstract_service.IRouteService;
import com.express_generation.back_end.infracture.mapper.RouteMapper;
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

import java.util.List;

@Service
@AllArgsConstructor
public class RouteService implements IRouteService {

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RouteMapper routeMapper;


    @Override
    public RouteResponse create(RouteRequest request) throws BadRequestException {

        //Convertimos a entidad
        RouteEntity routeEntity = routeMapper.toEntity(request);
        
        // recorremos la lista y buscamos por id
        List<OrderEntity> orders = orderRepository.findAllById(request.getOrderIds());
        if (orders.isEmpty()) {
            throw new BadRequestException(ErrorMessages.IdNotFound(request.getOrderIds().toString()));
        }
        
        //Agregamos todas las ordenes a una ruta
        for (OrderEntity order : orders) {

            order.setStatus(ShipmentStatus.ASSIGNED);
            order.setRoute(routeEntity);
        }
        routeEntity.setOrders(orders);

        // guardamos
        RouteEntity savedRoute = routeRepository.save(routeEntity);
        
        // retornamos la respuesta
        return routeMapper.toResponse(savedRoute);
    }

    @Override
    public Page<RouteResponse> getAll(int page, int size, SortType sortType) {
        if(page < 0)
            page = 0;
        PageRequest pagination = null;

        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.routeRepository.findAll(pagination).map(routeMapper::toResponse);
    }

    @Override
    public RouteResponse getById(String id) throws BadRequestException {

        RouteEntity routeEntity = find(id);

        return this.routeMapper.toResponse(routeEntity);
    }

    @Override
    public RouteResponse update(String id, RouteRequest routeRequest) throws BadRequestException {

        RouteEntity routeEntity = find(id);

        this.routeMapper.updateEntityFromRequest(routeRequest, routeEntity);
        routeEntity = routeRepository.save(routeEntity);

        return this.routeMapper.toResponse(routeEntity);
    }

    private RouteEntity find(String id) throws BadRequestException {

        return this.routeRepository.findById(id).orElseThrow(()
                        -> new BadRequestException(ErrorMessages.IdNotFound(id)));
    }

}
