package com.express_generation.back_end.domain.repositories;

import com.express_generation.back_end.domain.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {

    Optional<OrderEntity> findBytrackingNumber(String trackingNumber);

    List<OrderEntity> findByRouteId(String routeId);
    Optional<OrderEntity> findByOrderNumber(String orderNumber);
}

