package com.openx.zoo.apizoogestion.controllers;

import com.openx.zoo.apizoogestion.models.Supplier;
import com.openx.zoo.apizoogestion.services.SupplierService;
import com.openx.zoo.apizoogestion.utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/suppliers")
public class SupplierController {

    protected final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping(path = "")
    ResponseEntity<ApiResponse<List<Supplier>>> findAllSuppliers(){
        List<Supplier> suppliers = supplierService.findAllSuppliers();
        ApiResponse<List<Supplier>> listApiResponse = new ApiResponse<>(suppliers);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<ApiResponse<Supplier>> getSupplierById(@PathVariable Long id){
        Supplier supplierOpt = supplierService.getSupplierById(id);
        ApiResponse<Supplier> apiResponse = new ApiResponse<>(supplierOpt);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "")
    ResponseEntity<ApiResponse<Supplier>> createSupplier(@RequestBody Supplier supplier){
        Supplier supplierCreate = supplierService.createSupplier(supplier);
        ApiResponse<Supplier> apiResponse = new ApiResponse<>(supplierCreate);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping(path = "")
    ResponseEntity<ApiResponse<Supplier>> updateSupplier(@RequestBody Supplier supplier){
        Supplier supplierUpdate = supplierService.updateSupplier(supplier);
        ApiResponse<Supplier> apiResponse = new ApiResponse<>(supplierUpdate);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<ApiResponse<Boolean>> deleteSupplier(@PathVariable Long id){
        boolean state = supplierService.deleteSupplier(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(state);
        return ResponseEntity.ok(apiResponse);
    }
}