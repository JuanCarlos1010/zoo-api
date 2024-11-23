package com.openx.zoo.api.mapper;

import com.openx.zoo.api.dto.FoodAnimalDTO;
import com.openx.zoo.api.entity.Animal;
import com.openx.zoo.api.entity.Food;
import com.openx.zoo.api.entity.FoodAnimal;
import org.springframework.stereotype.Component;

@Component
public class FoodAnimalMapper extends AbstractMapper<FoodAnimal, FoodAnimalDTO> {

    @Override
    public FoodAnimalDTO toDTO(FoodAnimal foodAnimal) {
        return FoodAnimalDTO.builder()
                .id(foodAnimal.getId())
                .portion(foodAnimal.getPortion())
                .createdAt(foodAnimal.getCreatedAt())
                .updatedAt(foodAnimal.getUpdatedAt())
                .foodId(foodAnimal.getFood().getId())
                .foodName(foodAnimal.getFood().getName())
                .animalId(foodAnimal.getAnimal().getId())
                .animalName(foodAnimal.getAnimal().getName())
                .consumptionDate(foodAnimal.getConsumptionDate())
                .build();
    }

    @Override
    public FoodAnimal toEntity(FoodAnimalDTO foodAnimal) {
        return FoodAnimal.builder()
                .id(foodAnimal.getId())
                .portion(foodAnimal.getPortion())
                .createdAt(foodAnimal.getCreatedAt())
                .updatedAt(foodAnimal.getUpdatedAt())
                .food(new Food(foodAnimal.getFoodId()))
                .animal(new Animal(foodAnimal.getAnimalId()))
                .consumptionDate(foodAnimal.getConsumptionDate())
                .build();
    }
}
