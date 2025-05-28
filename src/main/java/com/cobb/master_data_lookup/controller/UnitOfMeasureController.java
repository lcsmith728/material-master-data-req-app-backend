package com.cobb.master_data_lookup.controller;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.UnitOfMeasure;
import com.cobb.master_data_lookup.request.MasterDataRequest;
import com.cobb.master_data_lookup.request.UnitOfMeasureRequest;
import com.cobb.master_data_lookup.response.ApiResponse;
import com.cobb.master_data_lookup.service.unitofmeasure.IUnitOfMeasureService;
import com.cobb.master_data_lookup.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping(UrlMapping.UOM)
@RequiredArgsConstructor
public class UnitOfMeasureController {

    private final IUnitOfMeasureService unitOfMeasureService;

    @RequestMapping(UrlMapping.UOM_CREATE)
    public ResponseEntity<ApiResponse> create(@RequestBody UnitOfMeasureRequest request) {
        try {
            UnitOfMeasure newUnitOfMeasure = unitOfMeasureService.create(request);
            return ResponseEntity.status(201).body(new ApiResponse("Unit of measure created successfully", newUnitOfMeasure));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.UOM_GET_BY_ID)
    public ResponseEntity<ApiResponse> getUnitOfMeasureById(@PathVariable Long id) {
        try {
            UnitOfMeasure unitOfMeasure = unitOfMeasureService.getUnitOfMeasureById(id);
            return ResponseEntity.ok(new ApiResponse("Unit of measure retrieved successfully", unitOfMeasure));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.UOM_UPDATE)
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody UnitOfMeasureRequest request) {
        try {
            UnitOfMeasure unitOfMeasure = unitOfMeasureService.update(request, id);
            return ResponseEntity.ok(new ApiResponse("Unit of measure updated successfully", unitOfMeasure));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.UOM_DELETE)
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        try {
            unitOfMeasureService.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("Unit of measure deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.UOM_GET_ALL)
    public ResponseEntity<ApiResponse> getAll() {
        try {
            return ResponseEntity.ok(new ApiResponse("Unit of measure retrieved successfully", unitOfMeasureService.getAllUnitOfMeasures()));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
