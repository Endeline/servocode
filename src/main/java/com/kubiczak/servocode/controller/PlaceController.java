package com.kubiczak.servocode.controller;

import com.kubiczak.servocode.model.PlaceEntity;
import com.kubiczak.servocode.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * This class hold all http request for place.
 * @author Henryk Kubiczak.
 * @version 1.0
 */
@Controller
@RequestMapping("/api/place/")
public class PlaceController {

    @Autowired
    private PlaceService service;

    @PostConstruct
    public void test(){
        PlaceEntity entity = new PlaceEntity(1,1);
        PlaceEntity entity2 = new PlaceEntity(1,1);
        PlaceEntity entity3 = new PlaceEntity(1,1);

        entity.setLatitude(1.0);
        entity.setLongitude(1.0);
        entity.setName("a");

        entity2.setLatitude(30.0);
        entity2.setLongitude(40.0);
        entity2.setName("b");

        entity3.setLatitude(50.0);
        entity3.setLongitude(60.0);
        entity3.setName("c");

        service.save(entity);
        service.save(entity2);
        service.save(entity3);
    }

    /**
     * Function hold http request for get all existing entities.
     * @return ResponseEntity with collection of PlaceEntity.
     */
    @GetMapping
    public ResponseEntity getAll(){
        return new ResponseEntity<>(
                service.getAll(),
                HttpStatus.OK
        );
    }

    /**
     * Function hold post http request for save new place entity.
     * @param entity {@link PlaceEntity}.
     * @return ResponseEntity with saved place entity.
     */
    @PostMapping
    public ResponseEntity save(@RequestBody PlaceEntity entity){
        return new ResponseEntity<>(
                service.save(entity),
                HttpStatus.OK
        );
    }

    /**
     * Function for hold post http request with collection of place entities.
     * @param entities {@link Collection} with {@link PlaceEntity}.
     * @return ResponseEntity with all added entities.
     */
    @PostMapping("places")
    public ResponseEntity saveAll(@RequestBody Collection<PlaceEntity> entities){
        return new ResponseEntity<>(
                service.save(entities),
                HttpStatus.OK
        );
    }

    /**
     * Hold post http request for search closer place.
     * @param entity {@link PlaceEntity}.
     * @return ResponseEntity with closer place.
     */
    @PostMapping("find")
    public ResponseEntity getCloserPoint(@RequestBody PlaceEntity entity){
        return new ResponseEntity<>(
                service.getCloserPoint(entity),
                HttpStatus.OK
        );
    }

    /**
     * Function hold http GET request with latitude and longitude as params, for search closet point.
     * @param latitude double
     * @param longitude double
     * @return ResponseEntity with closer point.
     */
    @GetMapping("find")
    public ResponseEntity getCloserPoint(@RequestParam double latitude, @RequestParam double longitude) {
        return new ResponseEntity<>(
                service.getCloserPoint(latitude, longitude),
                HttpStatus.OK
        );
    }
}
