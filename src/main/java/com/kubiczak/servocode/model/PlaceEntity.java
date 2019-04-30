package com.kubiczak.servocode.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Class for hold all information about entity in database and can be used in rest communication.
 * @author Henryk Kubiczak.
 * @version 1.0
 */
@Table(name = "place")
@Entity
@Data
@NoArgsConstructor
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

    /**
     * Used in search closer place.
     * @param latitude double.
     * @param longitude double.
     */
    public PlaceEntity(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
