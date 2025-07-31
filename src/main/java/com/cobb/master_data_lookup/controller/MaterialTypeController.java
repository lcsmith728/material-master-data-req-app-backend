package com.cobb.master_data_lookup.controller;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.MaterialType;
import com.cobb.master_data_lookup.request.MasterDataRequest;
import com.cobb.master_data_lookup.response.ApiResponse;
import com.cobb.master_data_lookup.service.materialtype.IMaterialTypeService;
import com.cobb.master_data_lookup.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping(UrlMapping.MATERIAL_TYPE)
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class MaterialTypeController {

    private final IMaterialTypeService materialTypeService;

    @RequestMapping(UrlMapping.MATERIAL_TYPE_CREATE)
    public ResponseEntity<ApiResponse> create(@RequestBody MasterDataRequest request) {
        try {
            MaterialType newMaterialType = materialTypeService.createMaterialType(request);
            return ResponseEntity.status(201).body(new ApiResponse("Material type created successfully", newMaterialType));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.MATERIAL_TYPE_GET_BY_ID)
    public ResponseEntity<ApiResponse> getMaterialTypeById(@PathVariable Long id) {
        try {
            MaterialType materialType = materialTypeService.getMaterialTypeById(id);
            return ResponseEntity.ok(new ApiResponse("Material type retrieved successfully", materialType));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping(UrlMapping.MATERIAL_TYPE_UPDATE)
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody MasterDataRequest request) {
        try {
            MaterialType materialType = materialTypeService.updateMaterialType(request, id);
            return ResponseEntity.ok(new ApiResponse("Material type updated successfully", materialType));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping(UrlMapping.MATERIAL_TYPE_DELETE)
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        try {
            materialTypeService.deleteMaterialTypeById(id);
            return ResponseEntity.ok(new ApiResponse("Material type deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.MATERIAL_TYPE_GET_ALL)
    public ResponseEntity<ApiResponse> getAllMaterialTypes() {
        try {
            return ResponseEntity.ok(new ApiResponse("Material types retrieved successfully", materialTypeService.getAllMaterialTypes()));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
