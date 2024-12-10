package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    private long id;
    private long roleId;
    private String phone;
    private String address;
    private String username;
    private String fullName;
    private String password;
    private String documentNumber;
}
