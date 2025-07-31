package com.cobb.master_data_lookup.controller;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.MrpController;
import com.cobb.master_data_lookup.request.MasterDataRequest;
import com.cobb.master_data_lookup.response.ApiResponse;
import com.cobb.master_data_lookup.service.mrpcontroller.IMrpControllerService;
import com.cobb.master_data_lookup.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping(UrlMapping.MRP_CONTROLLER)
@RequiredArgsConstructor
public class MrpControllerController {

    private final IMrpControllerService mrpControllerService;

    @RequestMapping(UrlMapping.MRP_CONTROLLER_CREATE)
    public ResponseEntity<ApiResponse> create(@RequestBody MasterDataRequest request) {
        try {
            MrpController newMrpController = mrpControllerService.create(request);
            return ResponseEntity.status(201).body(new ApiResponse("MrpController created successfully", newMrpController));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.MRP_CONTROLLER_GET_BY_ID)
    public ResponseEntity<ApiResponse> getMrpControllerById(@PathVariable Long id) {
        try {
            MrpController mrpController = mrpControllerService.getMrpControllerById(id);
            return ResponseEntity.ok(new ApiResponse("MrpController retrieved successfully", mrpController));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping(UrlMapping.MRP_CONTROLLER_UPDATE)
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody MasterDataRequest request) {
        try {
            MrpController mrpController = mrpControllerService.update(request, id);
            return ResponseEntity.ok(new ApiResponse("MrpController updated successfully", mrpController));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping(UrlMapping.MRP_CONTROLLER_DELETE)
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        try {
            mrpControllerService.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("MrpController deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.MRP_CONTROLLER_GET_ALL)
    public ResponseEntity<ApiResponse> getAllMrpControllers() {
        try {
            return ResponseEntity.ok(new ApiResponse("MrpControllers retrieved successfully", mrpControllerService.getAllMrpControllers()));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
