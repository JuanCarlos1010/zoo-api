package com.openx.zoo.api.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private long id;

    private String name;
    private String species;
    private LocalDateTime birthdate;
    private String gender;
    private String age;
    private String description;

    @Column(name = "entry_date")
    private LocalDateTime entryDate;

    @OneToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;
}
