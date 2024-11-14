package com.openx.zoo.api.controllers;

import com.openx.zoo.api.entities.Food;
import com.openx.zoo.api.services.FoodService;
import com.openx.zoo.api.utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<Food>>> findAllFoods(){
        List<Food> foods = foodService.findAllFoods();
        ApiResponse<List<Food>> listApiResponse = new ApiResponse<>(foods);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Food>> finFoodById(@PathVariable Long id){
        Food food = foodService.getFoodById(id);
        ApiResponse<Food> apiResponse = new ApiResponse<>(food);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "")
    public ResponseEntity<ApiResponse<Food>> createFood(@RequestBody Food food){
        Food foodCreate = foodService.createFood(food);
        ApiResponse<Food> apiResponse = new ApiResponse<>(foodCreate);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping(path = "")
    public ResponseEntity<ApiResponse<Food>> updateFood(@RequestBody Food food){
        Food foodUpdate = foodService.updateFood(food);
        ApiResponse<Food> apiResponse = new ApiResponse<>(foodUpdate);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteFood(@PathVariable Long id){
        boolean state = foodService.deleteFood(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(state);
        return ResponseEntity.ok(apiResponse);
    }
}
