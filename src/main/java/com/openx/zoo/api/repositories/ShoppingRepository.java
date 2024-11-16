package com.openx.zoo.api.repositories;

import com.openx.zoo.api.entities.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, Long> {
    Optional<Shopping> findByName(String name);
}
