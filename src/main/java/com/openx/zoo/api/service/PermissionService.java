package com.openx.zoo.api.service;

import com.openx.zoo.api.exception.InternalServerException;
import com.openx.zoo.api.exception.NotFoundException;
import com.openx.zoo.api.entity.Permission;
import com.openx.zoo.api.entity.Role;
import com.openx.zoo.api.entity.RolePermission;
import com.openx.zoo.api.repository.RolePermissionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {
    private final RolePermissionRepository rolePermissionRepository;

    public PermissionService(RolePermissionRepository rolePermissionRepository) {
        this.rolePermissionRepository = rolePermissionRepository;
    }

    public List<Permission> findAllPermissions() {
        return rolePermissionRepository.findPermissions();
    }

    public Permission findPermissionById(long id) {
        return rolePermissionRepository.findPermissionById(id)
                .orElseThrow(() -> new NotFoundException("Permission not found with id: " + id));
    }

    public List<Permission> findPermissionsByRole(long roleId) {
        return rolePermissionRepository.findPermissionsByRole(new Role(roleId));
    }

    public Role findRoleById(long id) {
        return rolePermissionRepository.findRoleById(id)
                .orElseThrow(() -> new NotFoundException("Role not found with id: " + id));
    }

    public Role findRoleByName(String name) {
        return rolePermissionRepository.findRoleByName(name)
                .orElseThrow(() -> new NotFoundException("Role not found with name: " + name));
    }

    public Role createRole(Role role) {
        try {
            Optional<Role> optionalRole = rolePermissionRepository.findRoleByName(role.getName());
            if (optionalRole.isPresent()) {
                throw new IllegalArgumentException("Role with name " + role.getName() + " already exists");
            }
            return rolePermissionRepository.createRole(role);
        } catch (Exception e) {
            throw new InternalServerException();
        }
    }

    public Role updateRole(Role role) {
        try {
            Role found = findRoleById(role.getId());
            found.setName(role.getName());
            return rolePermissionRepository.createRole(found);
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