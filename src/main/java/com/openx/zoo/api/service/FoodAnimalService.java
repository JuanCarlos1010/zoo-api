package com.openx.zoo.api.service;

import com.openx.zoo.api.exception.NotFoundException;
import com.openx.zoo.api.entity.Animal;
import com.openx.zoo.api.entity.Food;
import com.openx.zoo.api.entity.FoodAnimal;
import com.openx.zoo.api.repository.AnimalRepository;
import com.openx.zoo.api.repository.FoodAnimalRepository;
import com.openx.zoo.api.repository.FoodRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FoodAnimalService {
    private final FoodAnimalRepository foodAnimalRepository;
    private final FoodRepository foodRepository;
    private final AnimalRepository animalRepository;

    public FoodAnimalService(FoodAnimalRepository foodAnimalRepository, FoodRepository foodRepository, AnimalRepository animalRepository) {
        this.foodAnimalRepository = foodAnimalRepository;
        this.foodRepository = foodRepository;
        this.animalRepository = animalRepository;
    }

    public List<FoodAnimal> findAllFoodAnimals() {
        try {
            return foodAnimalRepository.findAll();
        } catch (Exception e) {
            throw new NotFoundException(e);
        }
    }

    public FoodAnimal getFoodAnimalById(Long id) {
        return foodAnimalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AlimentoAnimal no encontrado con id: " + id));
    }

    @Transactional
    public FoodAnimal createFoodAnimal(FoodAnimal createFoodAnimal) {
        Food food = createFoodAnimal.getFood();
        Animal animal = createFoodAnimal.getAnimal();
        if ((animal == null) && (food == null)) {
            throw new NotFoundException("El campo animal o alimento es obligatorio");
        } else {
            Optional<Animal> animalOtp = animalRepository.findById(createFoodAnimal.getId());
            if (animalOtp.isEmpty()) {
                assert animal != null;
                throw new NotFoundException("Animal no encontrado con id: " + animal.getId());
            } else {
                Optional<Food> foodOpt = foodRepository.findById(createFoodAnimal.getId());
                if (foodOpt.isEmpty()) {
                    throw new NotFoundException("Alimento no encontrado con id: " + food.getId());
                }
                createFoodAnimal.setAnimal(animalOtp.get());
                createFoodAnimal.setFood(foodOpt.get());
                return foodAnimalRepository.save(createFoodAnimal);
            }
        }
    }

    @Transactional
    public FoodAnimal updateFoodAnimal(FoodAnimal updateFoodAnimal) {
        Animal animal = updateFoodAnimal.getAnimal();
        Food food = updateFoodAnimal.getFood();
        if ((animal == null) && (food == null)) {
            throw new NotFoundException("El campo animal o alimento es obligatorio");
        }
        return foodAnimalRepository.findById(updateFoodAnimal.getId())
                .map(foodAnimal -> {
                    foodAnimal.setPortion(updateFoodAnimal.getPortion());
                    foodAnimal.setUpdatedAt(updateFoodAnimal.getUpdatedAt());
                    return foodAnimalRepository.save(foodAnimal);
                })
                .orElseThrow(() -> new NotFoundException("AnimalAlimento no encontrado con el id: " + updateFoodAnimal.getId()));
    }

    @Transactional
    public boolean deleteFoodAnimal(Long id) {
        FoodAnimal foodAnimal = getFoodAnimalById(id);
        foodAnimalRepository.delete(foodAnimal);
        return true;
    }
}
