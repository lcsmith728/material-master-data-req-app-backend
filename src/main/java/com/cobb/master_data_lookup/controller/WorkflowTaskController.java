package com.cobb.master_data_lookup.controller;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.WorkflowTask;
import com.cobb.master_data_lookup.request.WorkflowTaskRequest;
import com.cobb.master_data_lookup.response.ApiResponse;
import com.cobb.master_data_lookup.service.workflowTask.IWorkflowTask;
import com.cobb.master_data_lookup.service.workflowTask.WorkflowTaskService;
import com.cobb.master_data_lookup.utils.UrlMapping;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping(UrlMapping.WORKFLOW_TASK)
@RequiredArgsConstructor
public class WorkflowTaskController {

    private final IWorkflowTask workflowTaskService;

    @RequestMapping(UrlMapping.WORKFLOW_TASK_CREATE)
    public ResponseEntity<ApiResponse> createWorkflowTask(@RequestBody WorkflowTaskRequest request) {
        try {
            WorkflowTask newTask = workflowTaskService.create(request);
            return ResponseEntity.status(201).body(new ApiResponse("Workflow task created successfully", newTask));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    /**
     * This API is used to get all workflow tasks that were created by a user
     * @param createdBy the user who created the workflow task
     * @return a list of workflow tasks created by the user
     */
    @RequestMapping(UrlMapping.WORKFLOW_TASK_GET_ALL)
    public ResponseEntity<ApiResponse> getAllWorkflowTasksByCreatedBy(@PathVariable String createdBy) {
        try {
            return ResponseEntity.ok(new ApiResponse("Workflow tasks found successfully", workflowTaskService.getAllWorkflowTaskByCreatedBy(createdBy)));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.WORKFLOW_TASK_DELETE_ALL_BY_CREATED_BY)
    public ResponseEntity<ApiResponse> deleteAllWorkflowTasksByCreatedBy(@PathVariable String createdBy) {
        try {
            workflowTaskService.deleteAllWorkflowTaskByCreatedBy(createdBy);
            return ResponseEntity.ok(new ApiResponse("Workflow tasks deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping(UrlMapping.WORKFLOW_TASK_UPDATE)
    public ResponseEntity<ApiResponse> updateWorkflowTask(@PathVariable UUID id, @RequestBody WorkflowTaskRequest request) {
        try {
            return ResponseEntity.ok(new ApiResponse("Workflow task updated successfully", workflowTaskService.updateWorkflowTask(request, id)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
