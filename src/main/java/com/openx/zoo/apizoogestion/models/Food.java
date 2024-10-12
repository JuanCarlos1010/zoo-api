package com.openx.zoo.apizoogestion.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_food")
    private Long id;

    private String name;
    private String type;

    @Column(name = "unit_of_measurement")
    private String unitOfMeasurement;

    private int stock;
}
