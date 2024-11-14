package com.openx.zoo.api.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private long id;

    private String name;
}
