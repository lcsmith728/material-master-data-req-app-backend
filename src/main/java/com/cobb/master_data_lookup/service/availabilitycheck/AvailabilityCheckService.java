package com.cobb.master_data_lookup.service.availabilitycheck;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.AvailabilityCheck;
import com.cobb.master_data_lookup.repository.AvailabilityCheckRepository;
import com.cobb.master_data_lookup.request.AvailabilityCheckRequest;
import com.cobb.master_data_lookup.utils.AppMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailabilityCheckService implements IAvailabilityCheckService {

    private final AvailabilityCheckRepository availabilityCheckRepository;

    @Override
    public AvailabilityCheck createAvailabilityCheck(AvailabilityCheckRequest request) {
        AvailabilityCheck newAvailabilityCheck = new AvailabilityCheck();
        newAvailabilityCheck.setAvailabilityCheck(request.getAvailabilityCheck());
        return availabilityCheckRepository.save(newAvailabilityCheck);
    }

    @Override
    public AvailabilityCheck getAvailabilityCheckById(Long id) {
        return availabilityCheckRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(AppMessages.NOT_FOUND));
    }

    @Override
    public AvailabilityCheck updateAvailabilityCheck(AvailabilityCheckRequest request, Long id) {

        AvailabilityCheck existingAvailabilityCheck = getAvailabilityCheckById(id);
        existingAvailabilityCheck.setAvailabilityCheck(request.getAvailabilityCheck());
        return availabilityCheckRepository.save(existingAvailabilityCheck);
    }

    @Override
    public void deleteAvailabilityCheckById(Long id) {
        availabilityCheckRepository.findById(id)
                .ifPresentOrElse(availabilityCheckRepository::delete,
                        () -> { throw new ResourceNotFoundException(AppMessages.NOT_FOUND); });
    }

    @Override
    public List<AvailabilityCheck> getAllAvailabilityChecks() {
        return availabilityCheckRepository.findAll();
    }
}
