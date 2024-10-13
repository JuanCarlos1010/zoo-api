package com.openx.zoo.api.controllers;

import com.openx.zoo.api.models.Employee;
import com.openx.zoo.api.services.EmployeeService;
import com.openx.zoo.api.utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<Employee>>> getAllEmployees(){
        List<Employee> employees = employeeService.findAllEmployees();
        ApiResponse<List<Employee>> listApiResponse = new ApiResponse<>(employees);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<ApiResponse<Employee>> getEmployeeById(@PathVariable Long id){
        Employee employee = employeeService.getEmployeeById(id);
        ApiResponse<Employee> apiResponse = new ApiResponse<>(employee);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "")
    ResponseEntity<ApiResponse<Employee>> createEmployee(@RequestBody Employee employee){
        Employee employeeCreate = employeeService.createEmployee(employee);
        ApiResponse<Employee> apiResponse = new ApiResponse<>(employeeCreate);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping(path = "")
    ResponseEntity<ApiResponse<Employee>> updateEmployee(@RequestBody Employee employee){
        Employee employeeUpdate = employeeService.updateEmployee(employee);
        ApiResponse<Employee> apiResponse = new ApiResponse<>(employeeUpdate);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Boolean>> deleteEmployee(@PathVariable Long id){
        boolean state = employeeService.deleteEmployee(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(state);
        return ResponseEntity.ok(apiResponse);
    }
}
