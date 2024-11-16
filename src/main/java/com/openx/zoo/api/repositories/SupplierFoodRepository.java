package com.openx.zoo.api.repositories;

import com.openx.zoo.api.entities.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierFoodRepository extends JpaRepository<ShoppingItem, Long> {
}
