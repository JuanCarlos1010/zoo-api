package com.openx.zoo.api.controllers;

import com.openx.zoo.api.models.Zone;
import com.openx.zoo.api.services.ZoneService;
import com.openx.zoo.api.utility.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/zones")
public class ZoneController {
    private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<Zone>>> findAllZones(){
        List<Zone> zones = zoneService.findAllZones();
        ApiResponse<List<Zone>> listApiResponse = new ApiResponse<>(zones);
        return ResponseEntity.ok(listApiResponse);
    }

    @GetMapping(path = "/{id}")
     public ResponseEntity<ApiResponse<Zone>> getZoneById(@PathVariable Long id){
        Zone zone = zoneService.getZoneById(id);
        ApiResponse<Zone> apiResponse = new ApiResponse<>(zone);
        return ResponseEntity.ok(apiResponse);
     }

     @PostMapping(path = "")
     public ResponseEntity<ApiResponse<Zone>> createZone(@RequestBody Zone zona){
        Zone zoneCreate = zoneService.createZone(zona);
        ApiResponse<Zone> apiResponse = new ApiResponse<>(zoneCreate);
        return ResponseEntity.ok(apiResponse);
     }

     @PutMapping(path = "")
     public ResponseEntity<ApiResponse<Zone>> updateZone(@RequestBody Zone zona){
        Zone zoneUpdate = zoneService.updateZone(zona);
        ApiResponse<Zone> apiResponse = new ApiResponse<>(zoneUpdate);
        return ResponseEntity.ok(apiResponse);
     }

     @DeleteMapping(path = "/{id}")
     public ResponseEntity<ApiResponse<Boolean>> deleteZone(@PathVariable long id){
        boolean state = zoneService.deleteZone(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(state);
        return ResponseEntity.ok(apiResponse);
     }
}
