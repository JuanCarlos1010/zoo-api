package com.openx.zoo.api.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "food_animals")
public class FoodAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @OneToOne
    @JoinColumn(name = "food_id")
    private Food food;

    private double portion;

    @Column(name = "consumption_date")
    private LocalDateTime consumptionDate;
}
