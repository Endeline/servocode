package com.kubiczakk.servocode.service;

import com.kubiczakk.servocode.model.PlaceEntity;
import com.kubiczakk.servocode.restModel.PlacesPojo;

import java.util.Collection;

public interface PlaceService {
    Collection<PlaceEntity> getAll();

    PlaceEntity save(PlaceEntity entity);

    Collection<PlaceEntity> save(PlacesPojo entities);

    PlaceEntity getCloserPoint(PlaceEntity entity);
}
