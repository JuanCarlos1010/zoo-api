package com.openx.zoo.apizoogestion.controllers;

import com.openx.zoo.apizoogestion.models.Region;
import com.openx.zoo.apizoogestion.services.RegionService;
import com.openx.zoo.apizoogestion.utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/regions")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<Region>>> getAllRegiones(){
        List<Region> regions = regionService.findAllRegions();
        ApiResponse<List<Region>> listApiResponse = new ApiResponse<>(regions);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<ApiResponse<Region>> getRegionById(@PathVariable Long id){
        Region region = regionService.getRegionById(id);
        ApiResponse<Region> apiResponse = new ApiResponse<>(region);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "")
    ResponseEntity<ApiResponse<Region>> createRegion(@RequestBody Region region){
        Region regionCreate = regionService.createRegion(region);
        ApiResponse<Region> apiResponse = new ApiResponse<>(regionCreate);
        return ResponseEntity.ok(apiResponse) ;
    }

    @PutMapping(path = "")
    ResponseEntity<ApiResponse<Region>> updateRegion(@RequestBody Region region){
        Region regionUpdate = regionService.updateRegion(region);
        ApiResponse<Region> apiResponse = new ApiResponse<>(regionUpdate);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Boolean>> deleteRegion(@PathVariable Long id){
        boolean state = regionService.deleteRegion(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(state);
        return ResponseEntity.ok(apiResponse);

    }
}