package com.cobb.master_data_lookup.controller;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.StorageLocation;
import com.cobb.master_data_lookup.request.MasterDataRequest;
import com.cobb.master_data_lookup.response.ApiResponse;
import com.cobb.master_data_lookup.service.storagelocation.IStorageLocationService;
import com.cobb.master_data_lookup.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping(UrlMapping.STORAGE_LOCATION)
@RequiredArgsConstructor
public class StorageLocationController {

    private final IStorageLocationService storageLocationService;

    @RequestMapping(UrlMapping.STORAGE_LOCATION_CREATE)
    public ResponseEntity<ApiResponse> create(@RequestBody MasterDataRequest request) {
        try {
            StorageLocation newStorageLocation = storageLocationService.create(request);
            return ResponseEntity.status(201).body(new ApiResponse("StorageLocation created successfully", newStorageLocation));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.STORAGE_LOCATION_GET_BY_ID)
    public ResponseEntity<ApiResponse> getStorageLocationById(@PathVariable Long id) {
        try {
            StorageLocation storageLocation = storageLocationService.getStorageLocationById(id);
            return ResponseEntity.ok(new ApiResponse("StorageLocation retrieved successfully", storageLocation));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.STORAGE_LOCATION_UPDATE)
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody MasterDataRequest request) {
        try {
            StorageLocation storageLocation = storageLocationService.update(request, id);
            return ResponseEntity.ok(new ApiResponse("StorageLocation updated successfully", storageLocation));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping(UrlMapping.STORAGE_LOCATION_DELETE)
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        try {
            storageLocationService.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("StorageLocation deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.STORAGE_LOCATION_GET_ALL)
    public ResponseEntity<ApiResponse> getAll() {
        try {
            return ResponseEntity.ok(new ApiResponse("StorageLocation retrieved successfully", storageLocationService.getAllStorageLocations()));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
