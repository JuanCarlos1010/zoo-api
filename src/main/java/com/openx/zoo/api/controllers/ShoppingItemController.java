package com.openx.zoo.api.controllers;

import com.openx.zoo.api.dto.ShoppingItemDTO;
import com.openx.zoo.api.mappers.ShoppingItemMapper;
import com.openx.zoo.api.services.ShoppingItemService;
import com.openx.zoo.api.utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/zones")
public class ShoppingItemController {
    private final ShoppingItemMapper shoppingItemMapper;
    private final ShoppingItemService shoppingItemService;

    public ShoppingItemController(ShoppingItemMapper shoppingItemMapper, ShoppingItemService shoppingItemService) {
        this.shoppingItemMapper = shoppingItemMapper;
        this.shoppingItemService = shoppingItemService;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<ShoppingItemDTO>>> findAllShoppingItems() {
        List<ShoppingItemDTO> detailsList = shoppingItemMapper.toDTO(shoppingItemService.findAllShoppingItems());
        ApiResponse<List<ShoppingItemDTO>> listApiResponse = new ApiResponse<>(detailsList);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<ShoppingItemDTO>> getZoneById(@PathVariable Long id) {
        ShoppingItemDTO details = shoppingItemMapper.toDTO(shoppingItemService.getShoppingItemById(id));
        ApiResponse<ShoppingItemDTO> apiResponse = new ApiResponse<>(details);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "")
    public ResponseEntity<ApiResponse<ShoppingItemDTO>> createZone(@RequestBody ShoppingItemDTO body) {
        ShoppingItemDTO details = shoppingItemMapper.toDTO(shoppingItemService.createShoppingItem(shoppingItemMapper.toEntity(body)));
        ApiResponse<ShoppingItemDTO> apiResponse = new ApiResponse<>(details);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping(path = "")
    public ResponseEntity<ApiResponse<ShoppingItemDTO>> updateZone(@RequestBody ShoppingItemDTO body) {
        ShoppingItemDTO details = shoppingItemMapper.toDTO(shoppingItemService.updateShoppingItem(shoppingItemMapper.toEntity(body)));
        ApiResponse<ShoppingItemDTO> apiResponse = new ApiResponse<>(details);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteZone(@PathVariable long id) {
        boolean state = shoppingItemService.deleteShoppingItem(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(state);
        return ResponseEntity.ok(apiResponse);
    }
}
