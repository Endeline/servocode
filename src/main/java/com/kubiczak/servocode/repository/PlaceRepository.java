package com.kubiczak.servocode.repository;

import com.kubiczak.servocode.model.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<PlaceEntity, Long > {
}
