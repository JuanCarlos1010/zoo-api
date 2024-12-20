package com.openx.zoo.api.service;

import com.openx.zoo.api.exception.BadRequestException;
import com.openx.zoo.api.exception.InternalServerException;
import com.openx.zoo.api.exception.NotFoundException;
import com.openx.zoo.api.entity.Animal;
import com.openx.zoo.api.entity.Zone;
import com.openx.zoo.api.repository.AnimalRepository;
import com.openx.zoo.api.repository.ZoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final ZoneRepository zoneRepository;

    public AnimalService(AnimalRepository animalRepository, ZoneRepository zoneRepository) {
        this.animalRepository = animalRepository;
        this.zoneRepository = zoneRepository;
    }

    public List<Animal> findAllAnimals() {
        try {
            return animalRepository.findAllByDeletedAtIsNull();
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }

    public Animal getAnimalById(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Animal no encontrado con el id: " + id));
    }

    @Transactional
    public Animal createAnimal(Animal animal) {
        Zone zone = animal.getZone();
        if (zone == null) {
            throw new BadRequestException("El campo zona es obligatorio");
        } else {
            Optional<Zone> zoneOpt = zoneRepository.findById(zone.getId());
            if (zoneOpt.isEmpty()) {
                throw new NotFoundException("Zona no encontrada con id: " + zone.getId());
            }
            Optional<Animal> animalOpt = animalRepository.getByName(animal.getName());
            if (animalOpt.isPresent()) {
                throw new BadRequestException("Animal con el nombre '" + animal.getName() + "' ya existe");
            }
        }
        animal.setZone(zone);
        animal.setCreatedAt(LocalDateTime.now());
        return animalRepository.save(animal);
    }

    @Transactional
    public Animal updateAnimal(Animal updateAnimal) {
        Zone zone = updateAnimal.getZone();
        if (zone == null) {
            throw new NotFoundException("El campo zona es obligatorio");
        }
        return animalRepository.findById(updateAnimal.getId())
                .map(animal -> {
                    animal.setZone(zone);
                    animal.setAge(updateAnimal.getAge());
                    animal.setName(updateAnimal.getName());
                    animal.setUpdatedAt(LocalDateTime.now());
                    animal.setGender(updateAnimal.getGender());
                    animal.setSpecies(updateAnimal.getSpecies());
                    animal.setBirthdate(updateAnimal.getBirthdate());
                    animal.setEntryDate(updateAnimal.getEntryDate());
                    animal.setDescription(updateAnimal.getDescription());
                    return animalRepository.save(animal);
                })
                .orElseThrow(() -> new NotFoundException("Animal no econtrado con id: " + updateAnimal.getId()));
    }

    @Transactional
    public boolean deleteAnimal(Long id) {
        try {
            Animal animal = getAnimalById(id);
            animal.setDeletedAt(LocalDateTime.now());
            animalRepository.save(animal);
            return true;
        } catch (NotFoundException | BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }
}
