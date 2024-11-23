package com.openx.zoo.api.controller;

import com.openx.zoo.api.dto.EmployeeDTO;
import com.openx.zoo.api.mapper.EmployeeMapper;
import com.openx.zoo.api.service.EmployeeService;
import com.openx.zoo.api.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeMapper.toDTO(employeeService.findAllEmployees());
        ApiResponse<List<EmployeeDTO>> listApiResponse = new ApiResponse<>(employees);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<ApiResponse<EmployeeDTO>> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employee = employeeMapper.toDTO(employeeService.getEmployeeById(id));
        ApiResponse<EmployeeDTO> apiResponse = new ApiResponse<>(employee);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "")
    ResponseEntity<ApiResponse<EmployeeDTO>> createEmployee(@RequestBody EmployeeDTO body) {
        EmployeeDTO employee = employeeMapper.toDTO(employeeService.createEmployee(employeeMapper.toEntity(body)));
        ApiResponse<EmployeeDTO> apiResponse = new ApiResponse<>(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping(path = "")
    ResponseEntity<ApiResponse<EmployeeDTO>> updateEmployee(@RequestBody EmployeeDTO body) {
        EmployeeDTO employee = employeeMapper.toDTO(employeeService.updateEmployee(employeeMapper.toEntity(body)));
        ApiResponse<EmployeeDTO> apiResponse = new ApiResponse<>(employee);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Boolean>> deleteEmployee(@PathVariable Long id) {
        boolean state = employeeService.deleteEmployee(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(state);
        return ResponseEntity.ok(apiResponse);
    }
}
