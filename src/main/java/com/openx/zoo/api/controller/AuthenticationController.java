package com.openx.zoo.api.controller;

import com.openx.zoo.api.dto.SignInRequest;
import com.openx.zoo.api.dto.UserDTO;
import com.openx.zoo.api.dto.UserRequest;
import com.openx.zoo.api.dto.UserResponse;
import com.openx.zoo.api.mapper.UserMapper;
import com.openx.zoo.api.security.AuthenticationService;
import com.openx.zoo.api.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping(path = "/sign-up")
    public ResponseEntity<ApiResponse<UserDTO>> signUp(@RequestBody UserRequest requestBody) {
        UserDTO userResponse = userMapper.toDTO(authenticationService.signUp(requestBody));
        ApiResponse<UserDTO> apiResponse = new ApiResponse<>(userResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping(path = "/account")
    public ResponseEntity<ApiResponse<UserDTO>> updateAccount(
            @RequestParam(name = "updatePassword", defaultValue = "false") boolean updatePassword,
            @RequestBody UserRequest requestBody) {
        UserDTO userResponse = userMapper.toDTO(authenticationService.updateAccount(updatePassword, requestBody));
        ApiResponse<UserDTO> apiResponse = new ApiResponse<>(userResponse);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(path = "/account/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteAccount(@PathVariable(name = "id") long accountId) {
        boolean response = authenticationService.deleteAccount(accountId);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(response);
        return ResponseEntity.ok(apiResponse);
    }
}
