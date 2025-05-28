package com.cobb.master_data_lookup.controller;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.PurchasingGroup;
import com.cobb.master_data_lookup.request.MasterDataRequest;
import com.cobb.master_data_lookup.response.ApiResponse;
import com.cobb.master_data_lookup.service.purchasinggroup.IPurchasingGroupService;
import com.cobb.master_data_lookup.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping(UrlMapping.PURCHASING_GROUP)
@RequiredArgsConstructor
public class PurchasingGroupController {

    private final IPurchasingGroupService purchasingGroupService;

    @RequestMapping(UrlMapping.PURCHASING_GROUP_CREATE)
    public ResponseEntity<ApiResponse> create(@RequestBody MasterDataRequest request) {
        try {
            PurchasingGroup newPurchasingGroup = purchasingGroupService.create(request);
            return ResponseEntity.status(201).body(new ApiResponse("PurchasingGroup created successfully", newPurchasingGroup));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.PURCHASING_GROUP_GET_BY_ID)
    public ResponseEntity<ApiResponse> getPurchasingGroupById(@PathVariable Long id) {
        try {
            PurchasingGroup purchasingGroup = purchasingGroupService.getPurchaseGroupById(id);
            return ResponseEntity.ok(new ApiResponse("PurchasingGroup retrieved successfully", purchasingGroup));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.PURCHASING_GROUP_UPDATE)
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody MasterDataRequest request) {
        try {
            PurchasingGroup purchasingGroup = purchasingGroupService.update(request, id);
            return ResponseEntity.ok(new ApiResponse("PurchasingGroup updated successfully", purchasingGroup));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.PURCHASING_GROUP_DELETE)
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        try {
            purchasingGroupService.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("PurchasingGroup deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.PURCHASING_GROUP_GET_ALL)
    public ResponseEntity<ApiResponse> getAll() {
        try {
            return ResponseEntity.ok(new ApiResponse("PurchasingGroup retrieved successfully", purchasingGroupService.getAllPurchasingGroups()));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
