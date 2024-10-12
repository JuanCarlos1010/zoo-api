package com.openx.zoo.apizoogestion.repositories;

import com.openx.zoo.apizoogestion.models.SupplierFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SupplierFoodRepository extends JpaRepository<SupplierFood, Long> {
    //Optional<SupplierFood> findBydateOfSupply(LocalDateTime dateOfSupply);
}
