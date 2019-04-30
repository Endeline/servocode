package com.kubiczak.servocode.service;

import com.kubiczak.servocode.model.PlaceEntity;
import com.kubiczak.servocode.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Implementation of {@link PlaceService}.
 * @author Henryk Kubiczak.
 * @version 1.0
 */
@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository repository;

    @Override
    public Collection<PlaceEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public PlaceEntity save(PlaceEntity entity) {
        return repository.save(entity);
    }

    @Override
    public Collection<PlaceEntity> save(Collection<PlaceEntity> entities) {
        return entities
                .stream()
                .peek(entity -> repository.save(entity))
                .collect(Collectors.toList());
    }

    @Override
    public PlaceEntity getCloserPoint(PlaceEntity entity) {
        return getUserCloserPoint(entity);
    }

    @Override
    public PlaceEntity getCloserPoint(double latitude, double longitude) {
        return getUserCloserPoint(new PlaceEntity(latitude, longitude));
    }

    /**
     * Function for find closer point.
     * @param entity current place.
     * @return closer point.
     */
    private PlaceEntity getUserCloserPoint(PlaceEntity entity) {
        Collection<PlaceEntity> entities = repository.findAll();
        PlaceEntity response = null;

        //max double length.
        double lengthEquator = Double.MAX_VALUE;

        //iterate all collection.
        for (PlaceEntity place : entities) {
            double calculatedDistance = calculateDistance(
                    entity.getLatitude(),
                    place.getLatitude(),
                    entity.getLongitude(),
                    place.getLongitude()
            );

            //check point is closer than min current length to point.
            if (calculatedDistance < lengthEquator) {
                //save closer point and distance to this point.
                response = place;
                lengthEquator = calculatedDistance;

            }
        }

        return response;
    }

    /**
     * Function calculate distance between two point.
     * @param x1 latitude first point.
     * @param x2 latitude second point.
     * @param y1 longitude first point.
     * @param y2 longitude second point.
     * @return distance between point.
     */
    private double calculateDistance(double x1, double x2, double y1, double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
}
