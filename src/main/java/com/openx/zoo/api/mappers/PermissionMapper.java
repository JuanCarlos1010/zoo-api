package com.openx.zoo.api.mappers;

import com.openx.zoo.api.dto.PermissionDTO;
import com.openx.zoo.api.entities.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper extends AbstractMapper<Permission, PermissionDTO> {

    @Override
    public PermissionDTO toDTO(Permission permission) {
        return PermissionDTO.builder()
                .id(permission.getId())
                .name(permission.getName())
                .createdAt(permission.getCreatedAt())
                .updatedAt(permission.getUpdatedAt())
                .moduleName(permission.getModuleName())
                .description(permission.getDescription())
                .build();
    }

    @Override
    public Permission toEntity(PermissionDTO permission) {
        return Permission.builder()
                .id(permission.getId())
                .name(permission.getName())
                .createdAt(permission.getCreatedAt())
                .updatedAt(permission.getUpdatedAt())
                .moduleName(permission.getModuleName())
                .description(permission.getDescription())
                .build();
    }
}
