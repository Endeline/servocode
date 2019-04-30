package com.kubiczakk.servocode.repository;

import com.kubiczakk.servocode.model.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<PlaceEntity, Long > {
}
