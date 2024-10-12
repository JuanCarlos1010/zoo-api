package com.openx.zoo.apizoogestion.controllers;

import com.openx.zoo.apizoogestion.models.SupplierFood;
import com.openx.zoo.apizoogestion.services.SupplierFoodService;
import com.openx.zoo.apizoogestion.utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/supplier-foods")
public class SupplierFoodController {

    private final SupplierFoodService supplierFoodService;

    public SupplierFoodController(SupplierFoodService proveedorAlimentoService) {
        this.supplierFoodService = proveedorAlimentoService;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<SupplierFood>>> finAllSupplierFoods(){
        List<SupplierFood> supplierFoods = supplierFoodService.findAllSupplierFoods();
        ApiResponse<List<SupplierFood>> listApiResponse = new ApiResponse<>(supplierFoods);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<SupplierFood>> getSupplierFoodById(@PathVariable Long id){
        SupplierFood supplierFood = supplierFoodService.getSupplierFoodById(id);
        ApiResponse<SupplierFood> apiResponse = new ApiResponse<>(supplierFood);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "")
    ResponseEntity<ApiResponse<SupplierFood>> createSupplierFood(@RequestBody SupplierFood supplierFood){
        SupplierFood supplierFoodCreate = supplierFoodService.createSupplierFood(supplierFood);
        ApiResponse<SupplierFood> apiResponse = new ApiResponse<>(supplierFoodCreate);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping(path = "")
    ResponseEntity<ApiResponse<SupplierFood>> updateSupplierFood(@RequestBody SupplierFood supplierFood){
        SupplierFood supplierFoodUpdate = supplierFoodService.updateSupplierFood(supplierFood);
        ApiResponse<SupplierFood> apiResponse = new ApiResponse<>(supplierFoodUpdate);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<ApiResponse<Boolean>> deleteSupplierFood(@PathVariable Long id){
        boolean status = supplierFoodService.deleteSupplierFood(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(status);
        return ResponseEntity.ok(apiResponse);
    }
}
