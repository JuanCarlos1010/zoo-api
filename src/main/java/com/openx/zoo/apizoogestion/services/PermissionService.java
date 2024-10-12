package com.openx.zoo.apizoogestion.services;

import com.openx.zoo.apizoogestion.exceptions.InternalServerException;
import com.openx.zoo.apizoogestion.exceptions.NotFoundExeption;
import com.openx.zoo.apizoogestion.models.Permission;
import com.openx.zoo.apizoogestion.models.Role;
import com.openx.zoo.apizoogestion.models.RolePermission;
import com.openx.zoo.apizoogestion.repositories.RolePermissionRepository;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    private final RolePermissionRepository rolePermissionRepository;

    public PermissionService(RolePermissionRepository rolePermissionRepository) {
        this.rolePermissionRepository = rolePermissionRepository;
    }

    public Iterable<Permission> findAllPermissions() {
        return rolePermissionRepository.findPermissions();
    }

    public Permission findPermissionById(long id) {
        return rolePermissionRepository.findPermissionById(id)
                .orElseThrow(() -> new NotFoundExeption("Permission not found with id: " + id));
    }

    public Iterable<Permission> findPermissionsByRole(long roleId) {
        return rolePermissionRepository.findPermissionsByRole(roleId);
    }

    public Role findRoleById(long id) {
        return rolePermissionRepository.findRoleById(id)
                .orElseThrow(() -> new NotFoundExeption("Role not found with id: " + id));
    }

    public Role createRole(Role role) {
        try {
            return rolePermissionRepository.createRole(role);
        } catch (Exception e) {
            throw new InternalServerException();
        }
    }

    public Role update(Role role) {
        try {
            return rolePermissionRepository.createRole(role);
        } catch (Exception e) {
            throw new InternalServerException();
        }
    }

    public RolePermission createRolePermission(RolePermission rolePermission) {
        try {
            return rolePermissionRepository.createRolePermission(rolePermission);
        } catch (Exception e) {
            throw new InternalServerException();
        }
    }

    public boolean deleteRolePermission(long id) {
        try {
            return rolePermissionRepository.deleteRolePermission(id);
        } catch (Exception e) {
            throw new InternalServerException();
        }
    }
}
