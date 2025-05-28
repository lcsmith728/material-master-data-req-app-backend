package com.cobb.master_data_lookup.repository;

import com.cobb.master_data_lookup.model.StorageLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageLocationRepository extends JpaRepository<StorageLocation, Long> {
}
