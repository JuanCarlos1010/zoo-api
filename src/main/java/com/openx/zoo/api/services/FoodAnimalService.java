package com.openx.zoo.api.services;

import com.openx.zoo.api.exceptions.NotFoundExeption;
import com.openx.zoo.api.models.Animal;
import com.openx.zoo.api.models.Food;
import com.openx.zoo.api.models.FoodAnimal;
import com.openx.zoo.api.repositories.AnimalRepository;
import com.openx.zoo.api.repositories.FoodAnimalRepository;
import com.openx.zoo.api.repositories.FoodRepository;
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

    public List<FoodAnimal> findAllFoodAnimals(){
        try {
            return foodAnimalRepository.findAll();
        } catch (Exception e){
            throw new NotFoundExeption(e);
        }
    }

    public FoodAnimal getFoodAnimalById(Long id){
        return foodAnimalRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("AlimentoAnimal no encontrado con id: " + id));
    }

    @Transactional
    public FoodAnimal createFoodAnimal(FoodAnimal createFoodAnimal){
        Animal animal = createFoodAnimal.getAnimal();
        Food food = createFoodAnimal.getFood();
        if ((animal == null) && (food == null)) {
            throw new NotFoundExeption("El campo animal o alimento es obligatorio");
        } else {
            Optional<Animal> animalOtp = animalRepository.findById(createFoodAnimal.getId());
            if (animalOtp.isEmpty()) {
                assert animal != null;
                throw new NotFoundExeption("Animal no encontrado con id: " + animal.getId());
            } else {
                Optional<Food> foodOpt = foodRepository.findById(createFoodAnimal.getId());
                if (foodOpt.isEmpty()) {
                    throw new NotFoundExeption("Alimento no encontrado con id: " + food.getId());
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
        if ((animal == null) && (food == null)){
            throw new NotFoundExeption("El campo animal o alimento es obligatorio");
        }
        return foodAnimalRepository.findById(updateFoodAnimal.getId())
                .map(foodAnimal -> {
                    foodAnimal.setPortion(updateFoodAnimal.getPortion());
                    return foodAnimalRepository.save(foodAnimal);
                })
                .orElseThrow(() -> new NotFoundExeption("AnimalAlimento no encontrado con el id: " + updateFoodAnimal.getId()));
    }

    @Transactional
    public boolean deleteFoodAnimal(Long id){
        FoodAnimal foodAnimal = getFoodAnimalById(id);
        foodAnimalRepository.delete(foodAnimal);
        return true;
    }
}
