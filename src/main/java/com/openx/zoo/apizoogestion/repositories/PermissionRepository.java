package com.openx.zoo.apizoogestion.repositories;

import com.openx.zoo.apizoogestion.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
