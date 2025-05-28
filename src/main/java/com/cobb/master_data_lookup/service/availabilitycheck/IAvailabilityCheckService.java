package com.cobb.master_data_lookup.service.availabilitycheck;

import com.cobb.master_data_lookup.model.AvailabilityCheck;
import com.cobb.master_data_lookup.request.AvailabilityCheckRequest;

import java.util.List;

public interface IAvailabilityCheckService {

    AvailabilityCheck createAvailabilityCheck(AvailabilityCheckRequest request);
    AvailabilityCheck getAvailabilityCheckById(Long id);
    AvailabilityCheck updateAvailabilityCheck(AvailabilityCheckRequest request, Long id);
    void deleteAvailabilityCheckById(Long id);
    List<AvailabilityCheck> getAllAvailabilityChecks();

}
