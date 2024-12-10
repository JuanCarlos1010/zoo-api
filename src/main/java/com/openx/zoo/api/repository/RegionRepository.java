package com.openx.zoo.api.repository;

import com.openx.zoo.api.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByName(String name);
    List<Region> findByDeletedAtIsNull();
}
