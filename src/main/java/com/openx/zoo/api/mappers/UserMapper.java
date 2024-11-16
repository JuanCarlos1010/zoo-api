package com.openx.zoo.api.mappers;

import com.openx.zoo.api.dto.UserDTO;
import com.openx.zoo.api.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<User, UserDTO> {
    private final RoleMapper roleMapper;

    public UserMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .phone(user.getPhone())
                .address(user.getAddress())
                .username(user.getUsername())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .role(roleMapper.toDTO(user.getRole()))
                .documentNumber(user.getDocumentNumber())
                .build();
    }

    @Override
    public User toEntity(UserDTO user) {
        return User.builder()
                .id(user.getId())
                .phone(user.getPhone())
                .address(user.getAddress())
                .username(user.getUsername())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .documentNumber(user.getDocumentNumber())
                .role(roleMapper.toEntity(user.getRole()))
                .build();
    }
}
