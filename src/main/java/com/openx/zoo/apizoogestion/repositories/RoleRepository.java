package com.openx.zoo.apizoogestion.repositories;

import com.openx.zoo.apizoogestion.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
