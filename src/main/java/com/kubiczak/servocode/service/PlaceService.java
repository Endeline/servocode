package com.kubiczak.servocode.service;

import com.kubiczak.servocode.model.PlaceEntity;

import java.util.Collection;

public interface PlaceService {
    Collection<PlaceEntity> getAll();

    PlaceEntity save(PlaceEntity entity);

    Collection<PlaceEntity> save(Collection<PlaceEntity> entities);

    PlaceEntity getCloserPoint(PlaceEntity entity);

    PlaceEntity getCloserPoint(double latitude, double longitude);
}
