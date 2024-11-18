package com.openx.zoo.api.mappers;

import com.openx.zoo.api.dto.AnimalDTO;
import com.openx.zoo.api.entities.Animal;
import com.openx.zoo.api.entities.Zone;
import org.springframework.stereotype.Component;

@Component
public class AnimalMapper extends AbstractMapper<Animal, AnimalDTO> {

    @Override
    public AnimalDTO toDTO(Animal animal) {
        return AnimalDTO.builder()
                .id(animal.getId())
                .name(animal.getName())
                .age(animal.getAge())
                .birthdate(animal.getBirthdate())
                .updatedAt(animal.getUpdatedAt())
                .gender(animal.getGender())
                .species(animal.getSpecies())
                .zoneId(animal.getZone().getId())
                .zoneName(animal.getZone().getName())
                .build();
    }

    @Override
    public Animal toEntity(AnimalDTO animal) {
        return Animal.builder()
                .id(animal.getId())
                .name(animal.getName())
                .age(animal.getAge())
                .birthdate(animal.getBirthdate())
                .updatedAt(animal.getUpdatedAt())
                .gender(animal.getGender())
                .species(animal.getSpecies())
                .zone(new Zone(animal.getZoneId()))
                .build();
    }
}
