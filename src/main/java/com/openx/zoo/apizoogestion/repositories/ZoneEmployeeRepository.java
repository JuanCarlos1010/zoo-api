package com.openx.zoo.apizoogestion.repositories;

import com.openx.zoo.apizoogestion.models.ZoneEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneEmployeeRepository extends JpaRepository<ZoneEmployee, Long> {
}
