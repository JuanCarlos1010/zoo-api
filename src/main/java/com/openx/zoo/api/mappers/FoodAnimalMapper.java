package com.openx.zoo.api.mappers;

import com.openx.zoo.api.dto.FoodAnimalDTO;
import com.openx.zoo.api.entities.FoodAnimal;
import org.springframework.stereotype.Component;

@Component
public class FoodAnimalMapper extends AbstractMapper<FoodAnimal, FoodAnimalDTO> {
    private final FoodMapper foodMapper;
    private final AnimalMapper animalMapper;

    public FoodAnimalMapper(FoodMapper foodMapper, AnimalMapper animalMapper) {
        this.foodMapper = foodMapper;
        this.animalMapper = animalMapper;
    }

    @Override
    public FoodAnimalDTO toDTO(FoodAnimal foodAnimal) {
        return FoodAnimalDTO.builder()
                .id(foodAnimal.getId())
                .portion(foodAnimal.getPortion())
                .createdAt(foodAnimal.getCreatedAt())
                .updatedAt(foodAnimal.getUpdatedAt())
                .food(foodMapper.toDTO(foodAnimal.getFood()))
                .animal(animalMapper.toDTO(foodAnimal.getAnimal()))
                .consumptionDate(foodAnimal.getConsumptionDate())
                .build();
    }

    @Override
    public FoodAnimal toEntity(FoodAnimalDTO foodAnimal) {
        return FoodAnimal.builder()
                .id(foodAnimal.getId())
                .food(foodMapper.toEntity(foodAnimal.getFood()))
                .animal(animalMapper.toEntity(foodAnimal.getAnimal()))
                .portion(foodAnimal.getPortion())
                .createdAt(foodAnimal.getCreatedAt())
                .updatedAt(foodAnimal.getUpdatedAt())
                .consumptionDate(foodAnimal.getConsumptionDate())
                .build();
    }
}
