package com.kubiczakk.servocode.restModel;

import com.kubiczakk.servocode.model.PlaceEntity;
import lombok.Data;

import java.util.Collection;

@Data
public class PlacesPojo {
    private Collection<PlaceEntity> entities;
}
