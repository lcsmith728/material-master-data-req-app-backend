package com.cobb.master_data_lookup.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkflowTask {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long workflowId;

    private String workflowType;

    private String requestType;

    private String currentState;

    private String createdBy;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String workflowData;
}
