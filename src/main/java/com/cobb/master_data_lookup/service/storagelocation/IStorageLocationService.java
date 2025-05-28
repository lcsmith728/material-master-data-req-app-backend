package com.cobb.master_data_lookup.service.storagelocation;

import com.cobb.master_data_lookup.model.StorageLocation;
import com.cobb.master_data_lookup.request.MasterDataRequest;

import java.util.List;

public interface IStorageLocationService {

    StorageLocation create(MasterDataRequest request);
    StorageLocation getStorageLocationById(Long id);
    StorageLocation update(MasterDataRequest request, Long id);
    void deleteById(Long id);
    List<StorageLocation> getAllStorageLocations();
}
