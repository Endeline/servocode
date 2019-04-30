package com.kubiczakk.servocode.service;

import com.kubiczakk.servocode.model.PlaceEntity;
import com.kubiczakk.servocode.repository.PlaceRepository;
import com.kubiczakk.servocode.restModel.PlacesPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

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
    public Collection<PlaceEntity> save(PlacesPojo entities) {
        return entities.getEntities()
                .stream()
                .peek(entity -> repository.save(entity))
                .collect(Collectors.toList());
    }

    @Override
    public PlaceEntity getCloserPoint(PlaceEntity entity) {
        Collection<PlaceEntity> entities = repository.findAll();
        PlaceEntity response = null;

        double lengthEquator = 40075.0;

        for (PlaceEntity place : entities) {
            double calculatedDistance = calculateDistance(
                    entity.getLatitude(),
                    place.getLatitude(),
                    entity.getLongitude(),
                    place.getLongitude()
            );

            if (calculatedDistance < lengthEquator) {
                response = place;
                lengthEquator = calculatedDistance;

            }
        }

        return response;
    }

    private double calculateDistance(double x1, double x2, double y1, double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
}
