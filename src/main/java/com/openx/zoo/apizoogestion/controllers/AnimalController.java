package com.openx.zoo.apizoogestion.controllers;

import com.openx.zoo.apizoogestion.models.Animal;
import com.openx.zoo.apizoogestion.services.AnimalService;
import com.openx.zoo.apizoogestion.utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "animals")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<Animal>>> findAllAnimals(){
        List<Animal> animals = animalService.findAllAnimals();
        ApiResponse<List<Animal>> listApiResponse = new ApiResponse<>(animals);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Animal>> getAnimalById(@PathVariable Long id){
        Animal animal = animalService.getAnimalById(id);
        ApiResponse<Animal> apiResponse = new ApiResponse<>(animal);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "")
    public ResponseEntity<ApiResponse<Animal>> createAnimal(@RequestBody Animal animal){
        Animal animalCreate = animalService.createAnimal(animal);
        ApiResponse<Animal> apiResponse = new ApiResponse<>(animalCreate);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping(path = "")
    public ResponseEntity<ApiResponse<Animal>> updateAnimal(@RequestBody Animal animal){
        Animal animalUpdate = animalService.updateAnimal(animal);
        ApiResponse<Animal> apiResponse = new ApiResponse<>(animalUpdate);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteAnimal(@PathVariable long id){
        boolean state = animalService.deleteAnimal(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(state);
        return ResponseEntity.ok(apiResponse);
    }
}
