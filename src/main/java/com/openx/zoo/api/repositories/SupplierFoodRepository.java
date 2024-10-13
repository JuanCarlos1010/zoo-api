package com.openx.zoo.api.repositories;

import com.openx.zoo.api.models.SupplierFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierFoodRepository extends JpaRepository<SupplierFood, Long> {
}
