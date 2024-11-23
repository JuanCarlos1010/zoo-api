package com.openx.zoo.api.repository;

import com.openx.zoo.api.entity.ZoneEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneEmployeeRepository extends JpaRepository<ZoneEmployee, Long> {
}
