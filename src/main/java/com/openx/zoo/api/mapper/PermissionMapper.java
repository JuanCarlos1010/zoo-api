package com.openx.zoo.api.mapper;

import com.openx.zoo.api.dto.PermissionDTO;
import com.openx.zoo.api.entity.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper extends AbstractMapper<Permission, PermissionDTO> {

    @Override
    public PermissionDTO toDTO(Permission permission) {
        return PermissionDTO.builder()
                .id(permission.getId())
                .name(permission.getName())
                .moduleName(permission.getModuleName())
                .description(permission.getDescription())
                .build();
    }

    @Override
    public Permission toEntity(PermissionDTO permission) {
        return Permission.builder()
                .id(permission.getId())
                .name(permission.getName())
                .moduleName(permission.getModuleName())
                .description(permission.getDescription())
                .build();
    }
}
