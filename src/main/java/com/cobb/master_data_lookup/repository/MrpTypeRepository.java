package com.cobb.master_data_lookup.repository;

import com.cobb.master_data_lookup.model.MrpType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MrpTypeRepository extends JpaRepository<MrpType, Long> {
}
