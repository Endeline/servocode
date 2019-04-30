package com.kubiczakk.servocode.controller;

import com.kubiczakk.servocode.model.PlaceEntity;
import com.kubiczakk.servocode.restModel.PlacesPojo;
import com.kubiczakk.servocode.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/api/place/")
public class PlaceController {

    @Autowired
    private PlaceService service;

    @PostConstruct
    public void test(){
        PlaceEntity entity = new PlaceEntity();
        PlaceEntity entity2 = new PlaceEntity();
        PlaceEntity entity3 = new PlaceEntity();

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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAll(){
        return new ResponseEntity<>(
                service.getAll(),
                HttpStatus.OK
        );
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody PlaceEntity entity){
        return new ResponseEntity<>(
                service.save(entity),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "places", method = RequestMethod.POST)
    public ResponseEntity saveAll(@RequestBody PlacesPojo entities){
        return new ResponseEntity<>(
            service.save(entities),
            HttpStatus.OK
        );
    }

    @RequestMapping(value = "getCloserPoint", method = RequestMethod.POST)
    public ResponseEntity getCloserPoint(@RequestBody PlaceEntity entity){
        return new ResponseEntity<>(
                service.getCloserPoint(entity),
                HttpStatus.OK
        );
    }
}
