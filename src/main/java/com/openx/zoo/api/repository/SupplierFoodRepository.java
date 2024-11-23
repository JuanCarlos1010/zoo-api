package com.openx.zoo.api.repository;

import com.openx.zoo.api.entity.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierFoodRepository extends JpaRepository<ShoppingItem, Long> {
}
