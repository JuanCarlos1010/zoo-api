package com.openx.zoo.api.controllers;

import com.openx.zoo.api.dto.AnimalDTO;
import com.openx.zoo.api.mappers.AnimalMapper;
import com.openx.zoo.api.services.AnimalService;
import com.openx.zoo.api.utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/animals")
public class AnimalController {
    private final AnimalService animalService;
    private final AnimalMapper animalMapper;

    public AnimalController(AnimalService animalService, AnimalMapper animalMapper) {
        this.animalService = animalService;
        this.animalMapper = animalMapper;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<AnimalDTO>>> findAllAnimals(){
        List<AnimalDTO> animals = animalMapper.toDTO(animalService.findAllAnimals());
        ApiResponse<List<AnimalDTO>> listApiResponse = new ApiResponse<>(animals);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<AnimalDTO>> getAnimalById(@PathVariable Long id){
        AnimalDTO animal = animalMapper.toDTO(animalService.getAnimalById(id));
        ApiResponse<AnimalDTO> apiResponse = new ApiResponse<>(animal);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "")
    public ResponseEntity<ApiResponse<AnimalDTO>> createAnimal(@RequestBody AnimalDTO body){
        AnimalDTO animal = animalMapper.toDTO(animalService.createAnimal(animalMapper.toEntity(body)));
        ApiResponse<AnimalDTO> apiResponse = new ApiResponse<>(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping(path = "")
    public ResponseEntity<ApiResponse<AnimalDTO>> updateAnimal(@RequestBody AnimalDTO body){
        AnimalDTO animal = animalMapper.toDTO(animalService.createAnimal(animalMapper.toEntity(body)));
        ApiResponse<AnimalDTO> apiResponse = new ApiResponse<>(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteAnimal(@PathVariable long id){
        boolean state = animalService.deleteAnimal(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(state);
        return ResponseEntity.ok(apiResponse);
    }
}
