package com.openx.zoo.apizoogestion.repositories;

import com.openx.zoo.apizoogestion.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> getByName(String name);
}
