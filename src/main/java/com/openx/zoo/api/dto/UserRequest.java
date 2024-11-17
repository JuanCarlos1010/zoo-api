package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class UserRequest {
    private long id;
    private long role;
    private String phone;
    private String address;
    private String username;
    private String password;
    private String documentNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
