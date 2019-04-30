package com.kubiczakk.servocode.model;

import lombok.Data;

import javax.persistence.*;

@Table(name = "place")
@Entity
@Data
public class PlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;
}
