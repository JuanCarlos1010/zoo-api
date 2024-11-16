package com.openx.zoo.api.mappers;

import com.openx.zoo.api.dto.RoleDTO;
import com.openx.zoo.api.entities.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper extends AbstractMapper<Role, RoleDTO> {

    @Override
    public RoleDTO toDTO(Role role) {
        return RoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .createdAt(role.getCreatedAt())
                .updatedAt(role.getUpdatedAt())
                .build();
    }

    @Override
    public Role toEntity(RoleDTO role) {
        return Role.builder()
                .id(role.getId())
                .name(role.getName())
                .createdAt(role.getCreatedAt())
                .updatedAt(role.getUpdatedAt())
                .build();
    }
}
