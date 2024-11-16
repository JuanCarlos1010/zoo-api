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
    private long id;

    private double portion;

    @Column(name = "consumption_date")
    private LocalDateTime consumptionDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @OneToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
}
