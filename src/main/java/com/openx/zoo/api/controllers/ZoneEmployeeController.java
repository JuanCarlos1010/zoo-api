package com.openx.zoo.api.controllers;

import com.openx.zoo.api.models.ZoneEmployee;
import com.openx.zoo.api.services.ZoneEmployeeService;
import com.openx.zoo.api.utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/zone-employees")
public class ZoneEmployeeController {

    private final ZoneEmployeeService zoneEmployeeService;

    public ZoneEmployeeController(ZoneEmployeeService zoneEmployeeService) {
        this.zoneEmployeeService = zoneEmployeeService;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<ZoneEmployee>>> findAllZoneEmployees(){
        List<ZoneEmployee> zoneEmployees = zoneEmployeeService.findAllZoneEmployees();
        ApiResponse<List<ZoneEmployee>> listApiResponse = new ApiResponse<>(zoneEmployees);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<ZoneEmployee>> getZoneEmployeeById(@PathVariable Long id){
        ZoneEmployee zoneEmployee = zoneEmployeeService.getZoneEmployeeById(id);
        ApiResponse<ZoneEmployee> apiResponse = new ApiResponse<>(zoneEmployee);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "")
    public ResponseEntity<ApiResponse<ZoneEmployee>> createZoneEmployee(@RequestBody ZoneEmployee zoneEmployee){
        ZoneEmployee zoneEmployeeCreate = zoneEmployeeService.createZoneEmployee(zoneEmployee);
        ApiResponse<ZoneEmployee> apiResponse = new ApiResponse<>(zoneEmployeeCreate);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping(path = "")
    public ResponseEntity<ApiResponse<ZoneEmployee>> updateZoneEmployee(@RequestBody ZoneEmployee zoneEmployee){
        ZoneEmployee zoneEmployeeUpdate = zoneEmployeeService.updateZoneEmployee(zoneEmployee);
        ApiResponse<ZoneEmployee> apiResponse = new ApiResponse<>(zoneEmployeeUpdate);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteZoneEmployee(@PathVariable long id){
        boolean state = zoneEmployeeService.deleteZoneEmployee(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(state);
        return ResponseEntity.ok(apiResponse);
    }
}
