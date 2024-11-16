package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class UserDTO {
    private long id;
    private String username;
    private String phone;
    private String address;
    private String documentNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private RoleDTO role;
}
