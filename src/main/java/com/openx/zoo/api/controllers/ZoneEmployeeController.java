package com.openx.zoo.api.controllers;

import com.openx.zoo.api.dto.ZoneEmployeeDTO;
import com.openx.zoo.api.mappers.ZoneEmployeeMapper;
import com.openx.zoo.api.services.ZoneEmployeeService;
import com.openx.zoo.api.utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/zone-employees")
public class ZoneEmployeeController {

    private final ZoneEmployeeService zoneEmployeeService;
    private final ZoneEmployeeMapper zoneEmployeeMapper;

    public ZoneEmployeeController(ZoneEmployeeService zoneEmployeeService, ZoneEmployeeMapper zoneEmployeeMapper) {
        this.zoneEmployeeService = zoneEmployeeService;
        this.zoneEmployeeMapper = zoneEmployeeMapper;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<ZoneEmployeeDTO>>> findAllZoneEmployees() {
        List<ZoneEmployeeDTO> zoneEmployees = zoneEmployeeMapper.toDTO(zoneEmployeeService.findAllZoneEmployees());
        ApiResponse<List<ZoneEmployeeDTO>> listApiResponse = new ApiResponse<>(zoneEmployees);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<ZoneEmployeeDTO>> getZoneEmployeeById(@PathVariable Long id) {
        ZoneEmployeeDTO zoneEmployee = zoneEmployeeMapper.toDTO(zoneEmployeeService.getZoneEmployeeById(id));
        ApiResponse<ZoneEmployeeDTO> apiResponse = new ApiResponse<>(zoneEmployee);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "")
    public ResponseEntity<ApiResponse<ZoneEmployeeDTO>> createZoneEmployee(@RequestBody ZoneEmployeeDTO body) {
        ZoneEmployeeDTO zoneEmployee = zoneEmployeeMapper.toDTO(zoneEmployeeService.createZoneEmployee(zoneEmployeeMapper.toEntity(body)));
        ApiResponse<ZoneEmployeeDTO> apiResponse = new ApiResponse<>(zoneEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping(path = "")
    public ResponseEntity<ApiResponse<ZoneEmployeeDTO>> updateZoneEmployee(@RequestBody ZoneEmployeeDTO body) {
        ZoneEmployeeDTO zoneEmployee = zoneEmployeeMapper.toDTO(zoneEmployeeService.updateZoneEmployee(zoneEmployeeMapper.toEntity(body)));
        ApiResponse<ZoneEmployeeDTO> apiResponse = new ApiResponse<>(zoneEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteZoneEmployee(@PathVariable long id) {
        boolean state = zoneEmployeeService.deleteZoneEmployee(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(state);
        return ResponseEntity.ok(apiResponse);
    }
}
