package com.openx.zoo.api.mapper;

import com.openx.zoo.api.dto.AnimalDTO;
import com.openx.zoo.api.entity.Animal;
import com.openx.zoo.api.entity.Zone;
import org.springframework.stereotype.Component;

@Component
public class AnimalMapper extends AbstractMapper<Animal, AnimalDTO> {

    @Override
    public AnimalDTO toDTO(Animal animal) {
        return AnimalDTO.builder()
                .id(animal.getId())
                .age(animal.getAge())
                .name(animal.getName())
                .gender(animal.getGender())
                .species(animal.getSpecies())
                .birthdate(animal.getBirthdate())
                .updatedAt(animal.getUpdatedAt())
                .zoneId(animal.getZone().getId())
                .zoneName(animal.getZone().getName())
                .description(animal.getDescription())
                .build();
    }

    @Override
    public Animal toEntity(AnimalDTO animal) {
        return Animal.builder()
                .id(animal.getId())
                .age(animal.getAge())
                .name(animal.getName())
                .gender(animal.getGender())
                .species(animal.getSpecies())
                .birthdate(animal.getBirthdate())
                .updatedAt(animal.getUpdatedAt())
                .zone(new Zone(animal.getZoneId()))
                .description(animal.getDescription())
                .build();
    }
}
