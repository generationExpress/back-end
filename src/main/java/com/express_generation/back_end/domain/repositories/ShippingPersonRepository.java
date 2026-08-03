package com.express_generation.back_end.domain.repositories;

import com.express_generation.back_end.domain.entities.ShippingPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShippingPersonRepository extends JpaRepository<ShippingPersonEntity, String> {

    Optional<ShippingPersonEntity> findBydocumentNumber(String documentNumber);


}
