package com.openx.zoo.api.controllers;

import com.openx.zoo.api.dto.RegionDTO;
import com.openx.zoo.api.mappers.RegionMapper;
import com.openx.zoo.api.services.RegionService;
import com.openx.zoo.api.utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/regions")
public class RegionController {
    private final RegionService regionService;
    private final RegionMapper regionMapper;

    public RegionController(RegionMapper regionMapper, RegionService regionService) {
        this.regionMapper = regionMapper;
        this.regionService = regionService;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<RegionDTO>>> getAllRegions() {
        List<RegionDTO> regions = regionMapper.toDTO(regionService.findAllRegions());
        ApiResponse<List<RegionDTO>> listApiResponse = new ApiResponse<>(regions);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<ApiResponse<RegionDTO>> getRegionById(@PathVariable Long id) {
        RegionDTO region = regionMapper.toDTO(regionService.getRegionById(id));
        ApiResponse<RegionDTO> apiResponse = new ApiResponse<>(region);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "")
    ResponseEntity<ApiResponse<RegionDTO>> createRegion(@RequestBody RegionDTO regionDTO) {
        RegionDTO regionCreate = regionMapper.toDTO(regionService.createRegion(regionMapper.toEntity(regionDTO)));
        ApiResponse<RegionDTO> apiResponse = new ApiResponse<>(regionCreate);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping(path = "")
    ResponseEntity<ApiResponse<RegionDTO>> updateRegion(@RequestBody RegionDTO regionDTO) {
        RegionDTO regionUpdate = regionMapper.toDTO(regionService.updateRegion(regionMapper.toEntity(regionDTO)));
        ApiResponse<RegionDTO> apiResponse = new ApiResponse<>(regionUpdate);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Boolean>> deleteRegion(@PathVariable Long id) {
        boolean state = regionService.deleteRegion(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(state);
        return ResponseEntity.ok(apiResponse);
    }
}