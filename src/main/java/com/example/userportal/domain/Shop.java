package com.example.userportal.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "shop", schema = "testdb")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "description")
    private String description;

    public double calculateDistance(double latitude, double longitude) {
        return Math.sqrt(Math.pow((this.latitude - latitude), 2) + Math.pow((this.longitude - longitude), 2));
    }

}
