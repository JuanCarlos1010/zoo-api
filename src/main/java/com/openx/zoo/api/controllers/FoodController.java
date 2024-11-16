package com.openx.zoo.api.controllers;

import com.openx.zoo.api.dto.FoodDTO;
import com.openx.zoo.api.mappers.FoodMapper;
import com.openx.zoo.api.services.FoodService;
import com.openx.zoo.api.utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/foods")
public class FoodController {
    private final FoodService foodService;
    private final FoodMapper foodMapper;

    public FoodController(FoodService foodService, FoodMapper foodMapper) {
        this.foodService = foodService;
        this.foodMapper = foodMapper;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<FoodDTO>>> findAllFoods(){
        List<FoodDTO> foods = foodMapper.toDTO(foodService.findAllFoods());
        ApiResponse<List<FoodDTO>> listApiResponse = new ApiResponse<>(foods);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<FoodDTO>> finFoodById(@PathVariable Long id){
        FoodDTO food = foodMapper.toDTO(foodService.getFoodById(id));
        ApiResponse<FoodDTO> apiResponse = new ApiResponse<>(food);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "")
    public ResponseEntity<ApiResponse<FoodDTO>> createFood(@RequestBody FoodDTO body){
        FoodDTO food = foodMapper.toDTO(foodService.createFood(foodMapper.toEntity(body)));
        ApiResponse<FoodDTO> apiResponse = new ApiResponse<>(food);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping(path = "")
    public ResponseEntity<ApiResponse<FoodDTO>> updateFood(@RequestBody FoodDTO body){
        FoodDTO food = foodMapper.toDTO(foodService.updateFood(foodMapper.toEntity(body)));
        ApiResponse<FoodDTO> apiResponse = new ApiResponse<>(food);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteFood(@PathVariable Long id){
        boolean state = foodService.deleteFood(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(state);
        return ResponseEntity.ok(apiResponse);
    }
}
