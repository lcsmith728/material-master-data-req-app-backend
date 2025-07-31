package com.cobb.master_data_lookup.controller;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.AvailabilityCheck;
import com.cobb.master_data_lookup.request.AvailabilityCheckRequest;
import com.cobb.master_data_lookup.response.ApiResponse;
import com.cobb.master_data_lookup.service.availabilitycheck.IAvailabilityCheckService;
import com.cobb.master_data_lookup.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
@RequestMapping(UrlMapping.AVAILABILITY_CHECK)
public class AvailabilityCheckController {

    private final IAvailabilityCheckService availabilityCheckService;

    @RequestMapping(UrlMapping.AVAILABILITY_CHECK_CREATE)
    public ResponseEntity<ApiResponse> create(@RequestBody AvailabilityCheckRequest request) {
        try {
            AvailabilityCheck newAvailabilityCheck = availabilityCheckService.createAvailabilityCheck(request);
            return ResponseEntity.status(201).body(new ApiResponse("Availability check created successfully", newAvailabilityCheck));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.AVAILABILITY_CHECK_GET_BY_ID)
    public ResponseEntity<ApiResponse> getAvailabilityCheckById(@PathVariable Long id) {
        try {
            AvailabilityCheck availabilityCheck = availabilityCheckService.getAvailabilityCheckById(id);
            return ResponseEntity.ok(new ApiResponse("Availability check retrieved successfully", availabilityCheck));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping(UrlMapping.AVAILABILITY_CHECK_UPDATE)
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody AvailabilityCheckRequest request) {
        try {
            AvailabilityCheck availabilityCheck = availabilityCheckService.updateAvailabilityCheck(request, id);
            return ResponseEntity.ok(new ApiResponse("Availability check updated successfully", availabilityCheck));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping (UrlMapping.AVAILABILITY_CHECK_DELETE)
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        try {
            availabilityCheckService.deleteAvailabilityCheckById(id);
            return ResponseEntity.ok(new ApiResponse("Availability check deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.AVAILABILITY_CHECK_GET_ALL)
    public ResponseEntity<ApiResponse> getAllAvailabilityChecks() {
        try {
            List<AvailabilityCheck> availabilityChecks = availabilityCheckService.getAllAvailabilityChecks();
            return ResponseEntity.ok(new ApiResponse("Availability checks retrieved successfully", availabilityChecks));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
