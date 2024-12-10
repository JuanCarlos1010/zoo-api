package com.openx.zoo.api.controller;

import com.openx.zoo.api.dto.TreatmentDTO;
import com.openx.zoo.api.dto.MedicineDTO;
import com.openx.zoo.api.mapper.TreatmentMapper;
import com.openx.zoo.api.mapper.MedicineMapper;
import com.openx.zoo.api.service.TreatmentService;
import com.openx.zoo.api.utils.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "/treatments")
public class TreatmentController {
    private final TreatmentService service;
    private final MedicineMapper medicineMapper;
    private final TreatmentMapper treatmentMapper;

    public TreatmentController(TreatmentService service, MedicineMapper medicineMapper, TreatmentMapper treatmentMapper) {
        this.service = service;
        this.medicineMapper = medicineMapper;
        this.treatmentMapper = treatmentMapper;
    }

    @GetMapping(path = "")
    public ResponseEntity<ApiResponse<List<TreatmentDTO>>> findAllMedicalHistories() {
        List<TreatmentDTO> response = treatmentMapper.toDTO(service.findTreatments());
        ApiResponse<List<TreatmentDTO>> listApiResponse = new ApiResponse<>(response);
        return ResponseEntity.ok(listApiResponse);
    }

    @PostMapping(path = "")
    public ResponseEntity<ApiResponse<TreatmentDTO>> addTreatment(@RequestBody TreatmentDTO treatment) {
        TreatmentDTO response = treatmentMapper.toDTO(service.createTreatment(treatmentMapper.toEntity(treatment)));
        ApiResponse<TreatmentDTO> apiResponse = new ApiResponse<>(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping(path = "")
    public ResponseEntity<ApiResponse<TreatmentDTO>> updateTreatment(@RequestBody TreatmentDTO treatment) {
        TreatmentDTO response = treatmentMapper.toDTO(service.updateTreatment(treatmentMapper.toEntity(treatment)));
        ApiResponse<TreatmentDTO> apiResponse = new ApiResponse<>(response);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteMedicalHistory(@PathVariable(name = "id") long id) {
        boolean response = service.deleteTreatment(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(response);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(path = "/medicines")
    public ResponseEntity<ApiResponse<List<MedicineDTO>>> findAllMedicines() {
        List<MedicineDTO> response = medicineMapper.toDTO(service.findMedicines());
        ApiResponse<List<MedicineDTO>> listApiResponse = new ApiResponse<>(response);
        return ResponseEntity.ok(listApiResponse);
    }

    @PostMapping(path = "/medicines")
    public ResponseEntity<ApiResponse<MedicineDTO>> addMedicine(@RequestBody MedicineDTO medicine) {
        MedicineDTO response = medicineMapper.toDTO(service.createMedicine(medicineMapper.toEntity(medicine)));
        ApiResponse<MedicineDTO> apiResponse = new ApiResponse<>(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping(path = "/medicines")
    public ResponseEntity<ApiResponse<MedicineDTO>> updateMedicine(@RequestBody MedicineDTO vaccine) {
        MedicineDTO response = medicineMapper.toDTO(service.updateMedicine(medicineMapper.toEntity(vaccine)));
        ApiResponse<MedicineDTO> apiResponse = new ApiResponse<>(response);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping(path = "/medicines/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteMedicine(@PathVariable(name = "id") long id) {
        boolean response = service.deleteMedicine(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(response);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(path = "/download")
    public ResponseEntity<byte[]> download() {
        byte[] response = service.download();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("filename", "treatments.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .body(response);
    }
}
