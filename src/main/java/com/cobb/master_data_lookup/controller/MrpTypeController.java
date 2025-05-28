package com.cobb.master_data_lookup.controller;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.MrpType;
import com.cobb.master_data_lookup.request.MasterDataRequest;
import com.cobb.master_data_lookup.response.ApiResponse;
import com.cobb.master_data_lookup.service.mrptype.IMrpTypeService;
import com.cobb.master_data_lookup.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping(UrlMapping.MRP_TYPE)
@RequiredArgsConstructor
public class MrpTypeController {

    private final IMrpTypeService mrpTypeService;

    @RequestMapping(UrlMapping.MRP_TYPE_CREATE)
    public ResponseEntity<ApiResponse> create(@RequestBody MasterDataRequest request) {
        try {
            MrpType newMrpType = mrpTypeService.createMrpType(request);
            return ResponseEntity.status(201).body(new ApiResponse("Mrp type created successfully", newMrpType));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.MRP_TYPE_GET_BY_ID)
    public ResponseEntity<ApiResponse> getMrpTypeById(@PathVariable Long id) {
        try {
            MrpType mrpType = mrpTypeService.getMrpTypeById(id);
            return ResponseEntity.ok(new ApiResponse("Mrp type retrieved successfully", mrpType));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.MRP_TYPE_UPDATE)
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody MasterDataRequest request) {
        try {
            MrpType mrpType = mrpTypeService.updateMrpType(request, id);
            return ResponseEntity.ok(new ApiResponse("Mrp type updated successfully", mrpType));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.MRP_TYPE_DELETE)
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        try {
            mrpTypeService.deleteMrpTypeById(id);
            return ResponseEntity.ok(new ApiResponse("Mrp type deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.MRP_TYPE_GET_ALL)
    public ResponseEntity<ApiResponse> getAllMrpTypes() {
        try {
            return ResponseEntity.ok(new ApiResponse("Mrp types retrieved successfully", mrpTypeService.getAllMrpTypes()));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
