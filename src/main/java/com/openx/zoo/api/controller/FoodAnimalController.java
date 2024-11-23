package com.openx.zoo.api.controller;

import com.openx.zoo.api.dto.FoodAnimalDTO;
import com.openx.zoo.api.mapper.FoodAnimalMapper;
import com.openx.zoo.api.service.FoodAnimalService;
import com.openx.zoo.api.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/food-animals")
public class FoodAnimalController {
    private final FoodAnimalService foodAnimalService;
    private final FoodAnimalMapper foodAnimalMapper;

    public FoodAnimalController(FoodAnimalService foodAnimalService, FoodAnimalMapper foodAnimalMapper) {
        this.foodAnimalService = foodAnimalService;
        this.foodAnimalMapper = foodAnimalMapper;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<FoodAnimalDTO>>> findAllFoodAnimals() {
        List<FoodAnimalDTO> foodAnimals = foodAnimalMapper.toDTO(foodAnimalService.findAllFoodAnimals());
        ApiResponse<List<FoodAnimalDTO>> listApiResponse = new ApiResponse<>(foodAnimals);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<FoodAnimalDTO>> getFoodAnimalById(@PathVariable Long id) {
        FoodAnimalDTO foodAnimal = foodAnimalMapper.toDTO(foodAnimalService.getFoodAnimalById(id));
        ApiResponse<FoodAnimalDTO> apiResponse = new ApiResponse<>(foodAnimal);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "")
    ResponseEntity<ApiResponse<FoodAnimalDTO>> createFoodAnimal(@RequestBody FoodAnimalDTO body) {
        FoodAnimalDTO foodAnimal = foodAnimalMapper.toDTO(foodAnimalService.createFoodAnimal(foodAnimalMapper.toEntity(body)));
        ApiResponse<FoodAnimalDTO> apiResponse = new ApiResponse<>(foodAnimal);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping(path = "")
    ResponseEntity<ApiResponse<FoodAnimalDTO>> updateFoodAnimal(@RequestBody FoodAnimalDTO body) {
        FoodAnimalDTO foodAnimal = foodAnimalMapper.toDTO(foodAnimalService.updateFoodAnimal(foodAnimalMapper.toEntity(body)));
        ApiResponse<FoodAnimalDTO> apiResponse = new ApiResponse<>(foodAnimal);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<ApiResponse<Boolean>> deleteFoodAnimal(@PathVariable Long id) {
        boolean state = foodAnimalService.deleteFoodAnimal(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(state);
        return ResponseEntity.ok(apiResponse);
    }
}
