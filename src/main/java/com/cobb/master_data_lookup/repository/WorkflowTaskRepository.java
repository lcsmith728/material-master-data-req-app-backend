package com.cobb.master_data_lookup.repository;

import com.cobb.master_data_lookup.model.WorkflowTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkflowTaskRepository extends JpaRepository<WorkflowTask, UUID> {
}
