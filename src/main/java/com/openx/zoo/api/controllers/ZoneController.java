package com.openx.zoo.api.controllers;

import com.openx.zoo.api.dto.ZoneDTO;
import com.openx.zoo.api.mappers.ZoneMapper;
import com.openx.zoo.api.services.ZoneService;
import com.openx.zoo.api.utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/zones")
public class ZoneController {
    private final ZoneService zoneService;
    private final ZoneMapper zoneMapper;

    public ZoneController(ZoneService zoneService, ZoneMapper zoneMapper) {
        this.zoneService = zoneService;
        this.zoneMapper = zoneMapper;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<ZoneDTO>>> findAllZones() {
        List<ZoneDTO> zones = zoneMapper.toDTO(zoneService.findAllZones());
        ApiResponse<List<ZoneDTO>> listApiResponse = new ApiResponse<>(zones);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<ZoneDTO>> getZoneById(@PathVariable Long id) {
        ZoneDTO zone = zoneMapper.toDTO(zoneService.getZoneById(id));
        ApiResponse<ZoneDTO> apiResponse = new ApiResponse<>(zone);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "")
    public ResponseEntity<ApiResponse<ZoneDTO>> createZone(@RequestBody ZoneDTO body) {
        ZoneDTO zone = zoneMapper.toDTO(zoneService.createZone(zoneMapper.toEntity(body)));
        ApiResponse<ZoneDTO> apiResponse = new ApiResponse<>(zone);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping(path = "")
    public ResponseEntity<ApiResponse<ZoneDTO>> updateZone(@RequestBody ZoneDTO body) {
        ZoneDTO zone = zoneMapper.toDTO(zoneService.updateZone(zoneMapper.toEntity(body)));
        ApiResponse<ZoneDTO> apiResponse = new ApiResponse<>(zone);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteZone(@PathVariable long id) {
        boolean state = zoneService.deleteZone(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(state);
        return ResponseEntity.ok(apiResponse);
    }
}
