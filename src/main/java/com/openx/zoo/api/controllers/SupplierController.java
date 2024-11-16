package com.openx.zoo.api.controllers;

import com.openx.zoo.api.dto.SupplierDTO;
import com.openx.zoo.api.mappers.SupplierMapper;
import com.openx.zoo.api.services.SupplierService;
import com.openx.zoo.api.utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/suppliers")
public class SupplierController {
    protected final SupplierService supplierService;
    protected final SupplierMapper supplierMapper;

    public SupplierController(SupplierService supplierService, SupplierMapper supplierMapper) {
        this.supplierService = supplierService;
        this.supplierMapper = supplierMapper;
    }

    @GetMapping(path = "")
    ResponseEntity<ApiResponse<List<SupplierDTO>>> findAllSuppliers() {
        List<SupplierDTO> suppliers = supplierMapper.toDTO(supplierService.findAllSuppliers());
        ApiResponse<List<SupplierDTO>> listApiResponse = new ApiResponse<>(suppliers);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<ApiResponse<SupplierDTO>> getSupplierById(@PathVariable Long id) {
        SupplierDTO supplier = supplierMapper.toDTO(supplierService.getSupplierById(id));
        ApiResponse<SupplierDTO> apiResponse = new ApiResponse<>(supplier);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "")
    ResponseEntity<ApiResponse<SupplierDTO>> createSupplier(@RequestBody SupplierDTO body) {
        SupplierDTO supplier = supplierMapper.toDTO(supplierService.createSupplier(supplierMapper.toEntity(body)));
        ApiResponse<SupplierDTO> apiResponse = new ApiResponse<>(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping(path = "")
    ResponseEntity<ApiResponse<SupplierDTO>> updateSupplier(@RequestBody SupplierDTO body) {
        SupplierDTO supplier = supplierMapper.toDTO(supplierService.updateSupplier(supplierMapper.toEntity(body)));
        ApiResponse<SupplierDTO> apiResponse = new ApiResponse<>(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<ApiResponse<Boolean>> deleteSupplier(@PathVariable Long id) {
        boolean state = supplierService.deleteSupplier(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(state);
        return ResponseEntity.ok(apiResponse);
    }
}