package com.express_generation.back_end.domain.repositories;

import com.express_generation.back_end.domain.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, String> {
}
