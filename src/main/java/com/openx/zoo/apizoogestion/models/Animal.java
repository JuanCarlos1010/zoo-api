package com.openx.zoo.apizoogestion.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal")
    private long id;

    private String name;
    private String species;
    private LocalDateTime birthdate;
    private String gender;

    @Column(name = "date of entry")
    private LocalDateTime dateOfEntry;

    @OneToOne
    @JoinColumn(name = "id_zone")
    private Zone zone;
}
