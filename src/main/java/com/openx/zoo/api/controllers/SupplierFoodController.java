package com.openx.zoo.api.controllers;

import com.openx.zoo.api.entities.ShoppingItem;
import com.openx.zoo.api.services.SupplierFoodService;
import com.openx.zoo.api.utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/supplier-foods")
public class SupplierFoodController {

    private final SupplierFoodService supplierFoodService;

    public SupplierFoodController(SupplierFoodService supplierFoodService) {
        this.supplierFoodService = supplierFoodService;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<ShoppingItem>>> finAllSupplierFoods() {
        List<ShoppingItem> shoppingItems = supplierFoodService.findAllSupplierFoods();
        ApiResponse<List<ShoppingItem>> listApiResponse = new ApiResponse<>(shoppingItems);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<ShoppingItem>> getSupplierFoodById(@PathVariable Long id) {
        ShoppingItem shoppingItem = supplierFoodService.getSupplierFoodById(id);
        ApiResponse<ShoppingItem> apiResponse = new ApiResponse<>(shoppingItem);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "")
    ResponseEntity<ApiResponse<ShoppingItem>> createSupplierFood(@RequestBody ShoppingItem shoppingItem) {
        ShoppingItem shoppingItemCreate = supplierFoodService.createSupplierFood(shoppingItem);
        ApiResponse<ShoppingItem> apiResponse = new ApiResponse<>(shoppingItemCreate);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping(path = "")
    ResponseEntity<ApiResponse<ShoppingItem>> updateSupplierFood(@RequestBody ShoppingItem shoppingItem) {
        ShoppingItem shoppingItemUpdate = supplierFoodService.updateSupplierFood(shoppingItem);
        ApiResponse<ShoppingItem> apiResponse = new ApiResponse<>(shoppingItemUpdate);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<ApiResponse<Boolean>> deleteSupplierFood(@PathVariable Long id) {
        boolean status = supplierFoodService.deleteSupplierFood(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(status);
        return ResponseEntity.ok(apiResponse);
    }
}
