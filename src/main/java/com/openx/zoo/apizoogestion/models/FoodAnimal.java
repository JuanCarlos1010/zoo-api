package com.openx.zoo.apizoogestion.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "food_animals")
public class FoodAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_food_animal")
    private long id;

    @OneToOne
    @JoinColumn(name = "id_animal")
    private Animal animal;

    @OneToOne
    @JoinColumn(name = "id_food")
    private Food food;

    private Double portion;

    @Column(name = "consumption_date")
    private LocalDateTime consumptionDate;
}
