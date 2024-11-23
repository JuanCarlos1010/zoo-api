package com.openx.zoo.api.controller;

import com.openx.zoo.api.dto.ShoppingDTO;
import com.openx.zoo.api.mapper.ShoppingMapper;
import com.openx.zoo.api.service.ShoppingService;
import com.openx.zoo.api.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/shopping")
public class ShoppingController {
    private final ShoppingService shoppingService;
    private final ShoppingMapper shoppingMapper;

    public ShoppingController(ShoppingMapper shoppingMapper, ShoppingService shoppingService) {
        this.shoppingMapper = shoppingMapper;
        this.shoppingService = shoppingService;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<ShoppingDTO>>> findAllShopping() {
        List<ShoppingDTO> shoppingList = shoppingMapper.toDTO(shoppingService.findAllShopping());
        ApiResponse<List<ShoppingDTO>> listApiResponse = new ApiResponse<>(shoppingList);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<ShoppingDTO>> getShoppingById(@PathVariable Long id) {
        ShoppingDTO shopping = shoppingMapper.toDTO(shoppingService.getShoppingById(id));
        ApiResponse<ShoppingDTO> apiResponse = new ApiResponse<>(shopping);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "")
    public ResponseEntity<ApiResponse<ShoppingDTO>> createShopping(@RequestBody ShoppingDTO body) {
        ShoppingDTO shopping = shoppingMapper.toDTO(shoppingService.createShopping(shoppingMapper.toEntity(body)));
        ApiResponse<ShoppingDTO> apiResponse = new ApiResponse<>(shopping);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping(path = "")
    public ResponseEntity<ApiResponse<ShoppingDTO>> updateShopping(@RequestBody ShoppingDTO body) {
        ShoppingDTO shopping = shoppingMapper.toDTO(shoppingService.updateShopping(shoppingMapper.toEntity(body)));
        ApiResponse<ShoppingDTO> apiResponse = new ApiResponse<>(shopping);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteShopping(@PathVariable long id) {
        boolean state = shoppingService.deleteShopping(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(state);
        return ResponseEntity.ok(apiResponse);
    }
}
