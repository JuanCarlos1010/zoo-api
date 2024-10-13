package com.openx.zoo.api.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "zones")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zone_id")
    private long id;

    private String name;
    private String type;
    private int capacity;

    @OneToOne
    @JoinColumn(name = "region_id")
    private Region region;
}
