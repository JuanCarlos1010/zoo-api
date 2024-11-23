package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class UserDTO {
    private long id;
    private long roleId;
    private String phone;
    private String address;
    private String roleName;
    private String username;
    private String fullName;
    private String documentNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
