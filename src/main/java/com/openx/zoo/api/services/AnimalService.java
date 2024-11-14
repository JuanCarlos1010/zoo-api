package com.openx.zoo.api.services;

import com.openx.zoo.api.exceptions.InternalServerException;
import com.openx.zoo.api.exceptions.NotFoundException;
import com.openx.zoo.api.entities.Animal;
import com.openx.zoo.api.entities.Zone;
import com.openx.zoo.api.repositories.AnimalRepository;
import com.openx.zoo.api.repositories.ZoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
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
            return animalRepository.findAll();
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
            throw new NotFoundException("El campo zona es obligatorio");
        } else {
            Optional<Zone> zoneOpt = zoneRepository.findById(zone.getId());
            if (zoneOpt.isPresent()) {
                throw new NotFoundException("Zona no encontrada con id: " + zone.getId());
            }
            Optional<Animal> animalOpt = animalRepository.getByName(animal.getName());
            if (animalOpt.isPresent()) {
                throw new NotFoundException("Animal con el nombre " + animal.getName() + " ya existe.");
            }
        }
        animal.setZone(animal.getZone());
        return animalRepository.save(animal);
    }

    @Transactional
    public Animal updateAnimal(Animal updateAnimal) {
        return animalRepository.findById(updateAnimal.getId())
                .map(animal -> {
                    updateAnimal.setName(updateAnimal.getName());
                    updateAnimal.setSpecies(updateAnimal.getSpecies());
                    updateAnimal.setBirthdate(updateAnimal.getBirthdate());
                    updateAnimal.setGender(updateAnimal.getGender());
                    updateAnimal.setEntryDate(updateAnimal.getEntryDate());
                    return animalRepository.save(animal);
                })
                .orElseThrow(() -> new NotFoundException("Animal no econtrado con id: " + updateAnimal.getId()));
    }

    @Transactional
    public boolean deleteAnimal(Long id) {
        Animal animal = getAnimalById(id);
        animalRepository.delete(animal);
        return true;
    }
}
