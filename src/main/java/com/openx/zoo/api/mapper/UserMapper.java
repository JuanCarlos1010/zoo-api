package com.openx.zoo.api.mapper;

import com.openx.zoo.api.dto.UserDTO;
import com.openx.zoo.api.entity.Role;
import com.openx.zoo.api.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<User, UserDTO> {

    @Override
    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .phone(user.getPhone())
                .address(user.getAddress())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .roleId(user.getRole().getId())
                .roleName(user.getRole().getName())
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
                .fullName(user.getFullName())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .role(new Role(user.getRoleId()))
                .documentNumber(user.getDocumentNumber())
                .build();
    }
}
