package com.cobb.master_data_lookup.service.workflowTask;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.WorkflowTask;
import com.cobb.master_data_lookup.repository.WorkflowTaskRepository;
import com.cobb.master_data_lookup.request.WorkflowTaskRequest;
import com.cobb.master_data_lookup.utils.AppMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WorkflowTaskService implements IWorkflowTask {

    private final WorkflowTaskRepository workflowTaskRepository;
    @Override
    public WorkflowTask create(WorkflowTaskRequest request) {

        WorkflowTask newTask = new WorkflowTask();
        newTask.setWorkflowType("Material-Request");
        newTask.setRequestType("New-Material");
        newTask.setCurrentState("Incomplete");
        newTask.setCreatedBy(request.getCreatedBy());
        newTask.setWorkflowData(request.getWorkflowData());
        return workflowTaskRepository.save(newTask);
    }

    @Override
    public WorkflowTask getWorkflowTaskById(UUID id) {
        return null;
    }

    @Override
    public WorkflowTask updateWorkflowTask(WorkflowTaskRequest request, UUID id) {

        return null;
    }

    @Override
    public List<WorkflowTask> getAllWorkflowTask() {
        return null;
    }

    public List<WorkflowTask> getAllWorkflowTaskByCreatedBy(String createdBy) {
        try {
            return workflowTaskRepository.findAllByCreatedBy(createdBy);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(AppMessages.NOT_FOUND);
        }
    }

    @Override
    public void deleteAllWorkflowTaskByCreatedBy(String createdBy) {
        try {
            workflowTaskRepository.deleteAllByCreatedBy(createdBy);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(AppMessages.NOT_FOUND);
        }
    }
}
