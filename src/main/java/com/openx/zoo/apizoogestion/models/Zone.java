package com.openx.zoo.apizoogestion.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "zones")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zone")
    private long id;

    private String name;
    private String type;
    private int capacity;

    @OneToOne
    @JoinColumn(name = "id_region")
    private Region region;
}
