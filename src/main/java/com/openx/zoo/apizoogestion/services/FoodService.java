package com.openx.zoo.apizoogestion.services;

import com.openx.zoo.apizoogestion.exceptions.InternalServerException;
import com.openx.zoo.apizoogestion.exceptions.NotFoundExeption;
import com.openx.zoo.apizoogestion.models.Food;
import com.openx.zoo.apizoogestion.repositories.FoodRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository alimentoRepository) {
        this.foodRepository = alimentoRepository;
    }

    public List<Food> findAllFoods(){
        try {
            return foodRepository.findAll();
        } catch (Exception e){
            throw new InternalServerException(e);
        }
    }

    public Food getFoodById(Long id){
        return foodRepository.findById(id)
                .orElseThrow(() -> new NotFoundExeption("Alimento no encontrado con el id: " + id));
    }

    @Transactional
    public Food createFood(Food food){
        Optional<Food> foodOpt = foodRepository.findByName(food.getName());
        if (foodOpt.isPresent()){
            throw new NotFoundExeption("Alimento con el nombre " + food.getName() + "ya existe.");
        }
        return foodRepository.save(food);
    }

    @Transactional
    public Food updateFood(Food updateFood){
        return foodRepository.findById(updateFood.getId())
                .map(food -> {
                    food.setName(updateFood.getName());
                    food.setType(updateFood.getType());
                    food.setUnitOfMeasurement(updateFood.getUnitOfMeasurement());
                    food.setStock(updateFood.getStock());
                    return foodRepository.save(food);
                })
                .orElseThrow(() -> new NotFoundExeption("Alimento no encontrado con el id: " + updateFood.getId()));
    }

    @Transactional
    public boolean deleteFood(Long id){
        Food food = getFoodById(id);
        foodRepository.delete(food);
        return true;
    }
}
