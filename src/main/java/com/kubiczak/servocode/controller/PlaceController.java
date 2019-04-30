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

    @GetMapping
    public ResponseEntity getAll(){
        return new ResponseEntity<>(
                service.getAll(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity save(@RequestBody PlaceEntity entity){
        return new ResponseEntity<>(
                service.save(entity),
                HttpStatus.OK
        );
    }

    @PostMapping("places")
    public ResponseEntity saveAll(@RequestBody Collection<PlaceEntity> entities){
        return new ResponseEntity<>(
                service.save(entities),
                HttpStatus.OK
        );
    }

    @PostMapping("find")
    public ResponseEntity getCloserPoint(@RequestBody PlaceEntity entity){
        return new ResponseEntity<>(
                service.getCloserPoint(entity),
                HttpStatus.OK
        );
    }

    @GetMapping("find")
    public ResponseEntity getCloserPoint(@RequestParam double latitude, @RequestParam double longitude) {
        return new ResponseEntity<>(
                service.getCloserPoint(latitude, longitude),
                HttpStatus.OK
        );
    }
}
