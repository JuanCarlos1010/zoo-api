package com.openx.zoo.api.controllers;

import com.openx.zoo.api.dto.UserDTO;
import com.openx.zoo.api.mappers.UserMapper;
import com.openx.zoo.api.services.UserService;
import com.openx.zoo.api.utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<UserDTO>>> findAllUsers(){
        List<UserDTO> animals = userMapper.toDTO(userService.findAllUsers());
        ApiResponse<List<UserDTO>> listApiResponse = new ApiResponse<>(animals);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable Long id){
        UserDTO animal = userMapper.toDTO(userService.getUserById(id));
        ApiResponse<UserDTO> apiResponse = new ApiResponse<>(animal);
        return ResponseEntity.ok(apiResponse);
    }
}
