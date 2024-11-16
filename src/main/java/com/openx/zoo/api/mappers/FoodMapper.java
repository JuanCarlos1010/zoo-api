package com.openx.zoo.api.mappers;

import com.openx.zoo.api.dto.FoodDTO;
import com.openx.zoo.api.entities.Food;
import org.springframework.stereotype.Component;

@Component
public class FoodMapper extends AbstractMapper<Food, FoodDTO>{

    @Override
    public FoodDTO toDTO(Food food) {
        return FoodDTO.builder()
                .id(food.getId())
                .stock(food.getStock())
                .name(food.getName())
                .type(food.getType())
                .unitMeasurement(food.getUnitMeasurement())
                .createdAt(food.getCreatedAt())
                .updatedAt(food.getUpdatedAt())
                .build();
    }

    @Override
    public Food toEntity(FoodDTO food) {
        return Food.builder()
                .id(food.getId())
                .stock(food.getStock())
                .name(food.getName())
                .type(food.getType())
                .unitMeasurement(food.getUnitMeasurement())
                .createdAt(food.getCreatedAt())
                .updatedAt(food.getUpdatedAt())
                .build();
    }
}
