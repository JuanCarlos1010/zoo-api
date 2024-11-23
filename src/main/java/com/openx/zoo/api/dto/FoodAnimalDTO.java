package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class FoodAnimalDTO {
    private long id;
    private long foodId;
    private long animalId;
    private double portion;
    private String foodName;
    private String animalName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime consumptionDate;
}
