package com.express_generation.back_end.domain.repositories;

import com.express_generation.back_end.domain.entities.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, String> {
}
