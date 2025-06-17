package com.cobb.master_data_lookup.service.workflowTask;

import com.cobb.master_data_lookup.model.WorkflowTask;
import com.cobb.master_data_lookup.request.WorkflowTaskRequest;

import java.util.List;
import java.util.UUID;

public interface IWorkflowTask {

    WorkflowTask create(WorkflowTaskRequest request);

    WorkflowTask getWorkflowTaskById(UUID id);

    WorkflowTask updateWorkflowTask(WorkflowTaskRequest request, UUID id);

    List<WorkflowTask> getAllWorkflowTask();

    List<WorkflowTask> getAllWorkflowTaskByCreatedBy(String createdBy);

    void deleteAllWorkflowTaskByCreatedBy(String createdBy);
}
