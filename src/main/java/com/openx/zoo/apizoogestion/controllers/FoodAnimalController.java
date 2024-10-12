package com.openx.zoo.apizoogestion.controllers;

import com.openx.zoo.apizoogestion.models.FoodAnimal;
import com.openx.zoo.apizoogestion.services.FoodAnimalService;
import com.openx.zoo.apizoogestion.utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/food-animals")
public class FoodAnimalController {

    private final FoodAnimalService foodAnimalService;

    public FoodAnimalController(FoodAnimalService foodAnimalService) {
        this.foodAnimalService = foodAnimalService;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<FoodAnimal>>> findAllFoodAnimals(){
        List<FoodAnimal> alimentoAnimales = foodAnimalService.findAllFoodAnimals();
        ApiResponse<List<FoodAnimal>> listApiResponse = new ApiResponse<>(alimentoAnimales);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<FoodAnimal>> getFoodAnimalById(@PathVariable Long id){
        FoodAnimal foodAnimal = foodAnimalService.getFoodAnimalById(id);
        ApiResponse<FoodAnimal> apiResponse = new ApiResponse<>(foodAnimal);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "")
    ResponseEntity<ApiResponse<FoodAnimal>> createFoodAnimal(@RequestBody FoodAnimal foodAnimal){
        FoodAnimal foodAnimalCreate = foodAnimalService.createFoodAnimal(foodAnimal);
        ApiResponse<FoodAnimal> apiResponse = new ApiResponse<>(foodAnimalCreate);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping(path = "")
    ResponseEntity<ApiResponse<FoodAnimal>> updateFoodAnimal(@RequestBody FoodAnimal foodAnimal){
        FoodAnimal foodAnimalUpdate = foodAnimalService.updateFoodAnimal(foodAnimal);
        ApiResponse<FoodAnimal> apiResponse = new ApiResponse<>(foodAnimalUpdate);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<ApiResponse<Boolean>> deleteFoodAnimal(@PathVariable Long id){
        boolean state = foodAnimalService.deleteAlimentoAnimal(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(state);
        return ResponseEntity.ok(apiResponse);
    }
}
