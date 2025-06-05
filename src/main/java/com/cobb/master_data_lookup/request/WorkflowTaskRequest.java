package com.cobb.master_data_lookup.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class WorkflowTaskRequest {

    private String workflowType;

    private String requestType;

    private String currentState;

    private String createdBy;

    private LocalDateTime createdOn;

    private LocalDateTime updatedAt;

    private String workflowData;
}
