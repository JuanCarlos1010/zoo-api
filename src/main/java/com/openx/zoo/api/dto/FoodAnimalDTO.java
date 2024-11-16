package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class FoodAnimalDTO {
    private long id;
    private double portion;
    private LocalDateTime consumptionDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private FoodDTO food;
    private AnimalDTO animal;
}
