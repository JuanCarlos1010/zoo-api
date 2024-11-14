package com.openx.zoo.api.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private long id;

    private String name;
    private String type;
    private int stock;

    @Column(name = "unit_measurement")
    private String unitMeasurement;

}
