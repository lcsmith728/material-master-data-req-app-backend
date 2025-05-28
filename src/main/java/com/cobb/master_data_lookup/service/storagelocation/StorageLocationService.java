package com.cobb.master_data_lookup.service.storagelocation;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.StorageLocation;
import com.cobb.master_data_lookup.repository.StorageLocationRepository;
import com.cobb.master_data_lookup.request.MasterDataRequest;
import com.cobb.master_data_lookup.utils.AppMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StorageLocationService implements IStorageLocationService {

    private final StorageLocationRepository storageLocationRepository;

    @Override
    public StorageLocation create(MasterDataRequest request) {
        StorageLocation storageLocation = new StorageLocation();
        storageLocation.setStorageLocation(request.getName());
        storageLocation.setStorageLocationDescription(request.getNameDescription());
        return storageLocationRepository.save(storageLocation);
    }

    @Override
    public StorageLocation getStorageLocationById(Long id) {
        return storageLocationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(AppMessages.NOT_FOUND)
        );
    }

    @Override
    public StorageLocation update(MasterDataRequest request, Long id) {
        StorageLocation storageLocation = getStorageLocationById(id);
        storageLocation.setStorageLocation(request.getName());
        storageLocation.setStorageLocationDescription(request.getNameDescription());
        return storageLocationRepository.save(storageLocation);
    }

    @Override
    public void deleteById(Long id) {
        if (storageLocationRepository.existsById(id)) {
            storageLocationRepository.deleteById(id);
        }
    }

    @Override
    public List<StorageLocation> getAllStorageLocations() {
        return storageLocationRepository.findAll();
    }
}
