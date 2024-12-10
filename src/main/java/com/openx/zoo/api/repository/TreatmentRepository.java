package com.openx.zoo.api.repository;

import com.openx.zoo.api.entity.Animal;
import com.openx.zoo.api.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    List<Treatment> findByAnimal(Animal animal);
    List<Treatment> findAllByDeletedAtIsNull();
}
