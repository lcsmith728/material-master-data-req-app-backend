package com.cobb.master_data_lookup.controller;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.MaterialGroup;
import com.cobb.master_data_lookup.request.MasterDataRequest;
import com.cobb.master_data_lookup.response.ApiResponse;
import com.cobb.master_data_lookup.service.materialgroup.IMaterialGroupService;
import com.cobb.master_data_lookup.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlMapping.MATERIAL_GROUP)
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class MaterialGroupController {

    private final IMaterialGroupService materialGroupService;

    @RequestMapping(UrlMapping.MATERIAL_GROUP_CREATE)
    public ResponseEntity<ApiResponse> create(@RequestBody MasterDataRequest request) {
        try {
            MaterialGroup newMaterialGroup = materialGroupService.createMaterialGroup(request);
            return ResponseEntity.status(201).body(new ApiResponse("Material group created successfully", newMaterialGroup));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.MATERIAL_GROUP_GET_BY_ID)
    public ResponseEntity<ApiResponse> getMaterialGroupById(@PathVariable Long id) {
        try {
            MaterialGroup materialGroup = materialGroupService.getMaterialGroupById(id);
            return ResponseEntity.ok(new ApiResponse("Material group retrieved successfully", materialGroup));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.MATERIAL_GROUP_UPDATE)
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody MasterDataRequest request) {
        try {
            MaterialGroup materialGroup = materialGroupService.updateMaterialGroup(request, id);
            return ResponseEntity.ok(new ApiResponse("Material group updated successfully", materialGroup));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping(UrlMapping.MATERIAL_GROUP_DELETE)
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        try {
            materialGroupService.deleteMaterialGroupById(id);
            return ResponseEntity.ok(new ApiResponse("Material group deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.MATERIAL_GROUP_GET_ALL)
    public ResponseEntity<ApiResponse> getAllMaterialGroups() {
        try {
            List<MaterialGroup> materialGroups = materialGroupService.getAllMaterialGroups();
            return ResponseEntity.ok(new ApiResponse("Material groups retrieved successfully", materialGroups));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
