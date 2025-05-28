package com.cobb.master_data_lookup.repository;

import com.cobb.master_data_lookup.model.AvailabilityCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityCheckRepository extends JpaRepository<AvailabilityCheck, Long> {
}
