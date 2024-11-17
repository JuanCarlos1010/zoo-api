package com.openx.zoo.api.controllers;

import com.openx.zoo.api.dto.SignInRequest;
import com.openx.zoo.api.dto.UserDTO;
import com.openx.zoo.api.dto.UserRequest;
import com.openx.zoo.api.dto.UserResponse;
import com.openx.zoo.api.mappers.UserMapper;
import com.openx.zoo.api.security.AuthenticationService;
import com.openx.zoo.api.utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    public AuthenticationController(AuthenticationService authenticationService, UserMapper userMapper) {
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
    }

    @PostMapping(path = "/sign-in")
    public ResponseEntity<ApiResponse<UserResponse>> signIn(@RequestBody SignInRequest signInRequest) {
        UserResponse userResponse = authenticationService.signIn(signInRequest);
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>(userResponse);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "/account")
    public ResponseEntity<ApiResponse<UserDTO>> signUp(@RequestBody UserRequest requestBody) {
        UserDTO userResponse = userMapper.toDTO(authenticationService.signUp(requestBody));
        ApiResponse<UserDTO> apiResponse = new ApiResponse<>(userResponse);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping(path = "/account")
    public ResponseEntity<ApiResponse<UserDTO>> updateAccount(@RequestBody UserDTO requestBody) {
        UserDTO userResponse = userMapper.toDTO(authenticationService.updateAccount(userMapper.toEntity(requestBody)));
        ApiResponse<UserDTO> apiResponse = new ApiResponse<>(userResponse);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(path = "/account/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAccount(@PathVariable(name = "id") long accountId) {
        Void response = authenticationService.deleteAccount(accountId);
        ApiResponse<Void> apiResponse = new ApiResponse<>(response);
        return ResponseEntity.ok(apiResponse);
    }
}
