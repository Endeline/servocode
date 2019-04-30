package com.kubiczak.servocode.service;

import com.kubiczak.servocode.model.PlaceEntity;

import java.util.Collection;

/**
 * Interface hold all public method for any implementation place service
 * @author Henryk Kubiczak
 * @version 1.0
 */
public interface PlaceService {
    /**
     * Function return collection with all existing {@link PlaceEntity}.
     * @return collection with place entity.
     */
    Collection<PlaceEntity> getAll();

    /**
     * Function for save new entity.
     * @param entity {@link PlaceEntity}.
     * @return saved entity.
     */
    PlaceEntity save(PlaceEntity entity);

    /**
     * Function for save one or more place entities.
     * @param entities {@link Collection} of {@link PlaceEntity}.
     * @return collection with saved entities.
     */
    Collection<PlaceEntity> save(Collection<PlaceEntity> entities);

    /**
     * Function for search closer point by pojo.
     * @param entity {@link PlaceEntity}.
     * @return closer place.
     */
    PlaceEntity getCloserPoint(PlaceEntity entity);

    /**
     * Function for search closer point by latitude and longitude.
     * @param latitude double
     * @param longitude double
     * @return closer place.
     */
    PlaceEntity getCloserPoint(double latitude, double longitude);
}
