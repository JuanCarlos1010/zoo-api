package com.openx.zoo.api.mapper;

import com.openx.zoo.api.dto.FoodDTO;
import com.openx.zoo.api.entity.Food;
import org.springframework.stereotype.Component;

@Component
public class FoodMapper extends AbstractMapper<Food, FoodDTO>{

    @Override
    public FoodDTO toDTO(Food food) {
        return FoodDTO.builder()
                .id(food.getId())
                .name(food.getName())
                .type(food.getType())
                .stock(food.getStock())
                .createdAt(food.getCreatedAt())
                .updatedAt(food.getUpdatedAt())
                .unitMeasurement(food.getUnitMeasurement())
                .build();
    }

    @Override
    public Food toEntity(FoodDTO food) {
        return Food.builder()
                .id(food.getId())
                .name(food.getName())
                .type(food.getType())
                .stock(food.getStock())
                .createdAt(food.getCreatedAt())
                .updatedAt(food.getUpdatedAt())
                .unitMeasurement(food.getUnitMeasurement())
                .build();
    }
}
