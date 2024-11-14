package com.openx.zoo.api.repositories;

import com.openx.zoo.api.entities.ZoneEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneEmployeeRepository extends JpaRepository<ZoneEmployee, Long> {
}
