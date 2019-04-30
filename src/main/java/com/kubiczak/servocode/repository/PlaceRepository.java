package com.kubiczak.servocode.repository;

import com.kubiczak.servocode.model.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository of place.
 * @author Henryk Kubiczak.
 * @version 1.0
 */
public interface PlaceRepository extends JpaRepository<PlaceEntity, Long > {
}
