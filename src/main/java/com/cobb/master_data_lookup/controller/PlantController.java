package com.cobb.master_data_lookup.controller;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.Plant;
import com.cobb.master_data_lookup.request.MasterDataRequest;
import com.cobb.master_data_lookup.request.PlantRequest;
import com.cobb.master_data_lookup.response.ApiResponse;
import com.cobb.master_data_lookup.service.plant.IPlantService;
import com.cobb.master_data_lookup.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping(UrlMapping.PLANT)
@RequiredArgsConstructor
public class PlantController {

    private final IPlantService plantService;

    @RequestMapping(UrlMapping.PLANT_CREATE)
    public ResponseEntity<ApiResponse> create(@RequestBody PlantRequest request) {
        try {
            Plant newPlant = plantService.create(request);
            return ResponseEntity.status(201).body(new ApiResponse("Plant created successfully", newPlant));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.PLANT_GET_BY_ID)
    public ResponseEntity<ApiResponse> getPlantById(@PathVariable Long id) {
        try {
            Plant plant = plantService.getPlantById(id);
            return ResponseEntity.ok(new ApiResponse("Plant retrieved successfully", plant));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.PLANT_UPDATE)
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody PlantRequest request) {
        try {
            Plant plant = plantService.update(request, id);
            return ResponseEntity.ok(new ApiResponse("Plant updated successfully", plant));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping(UrlMapping.PLANT_DELETE)
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        try {
            plantService.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("Plant deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.PLANT_GET_ALL)
    public ResponseEntity<ApiResponse> getAllPlants() {
        try {
            return ResponseEntity.ok(new ApiResponse("Plants retrieved successfully", plantService.getAllPlants()));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
