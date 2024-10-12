package com.openx.zoo.apizoogestion.repositories;

import com.openx.zoo.apizoogestion.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Optional<Animal> getByName(String name);
}
