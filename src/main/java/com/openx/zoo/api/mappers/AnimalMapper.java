package com.openx.zoo.api.mappers;

import com.openx.zoo.api.dto.AnimalDTO;
import com.openx.zoo.api.entities.Animal;
import org.springframework.stereotype.Component;

@Component
public class AnimalMapper extends AbstractMapper<Animal, AnimalDTO> {
    private final ZoneMapper zoneMapper;

    public AnimalMapper(ZoneMapper zoneMapper) {
        this.zoneMapper = zoneMapper;
    }

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
                .zone(zoneMapper.toDTO(animal.getZone()))
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
                .zone(zoneMapper.toEntity(animal.getZone()))
                .build();
    }
}
