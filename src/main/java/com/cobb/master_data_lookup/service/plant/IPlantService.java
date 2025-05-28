package com.cobb.master_data_lookup.service.plant;

import com.cobb.master_data_lookup.model.Plant;
import com.cobb.master_data_lookup.request.PlantRequest;

import java.util.List;

public interface IPlantService {
    Plant create(PlantRequest request);
    Plant getPlantById(Long id);
    Plant update(PlantRequest request, Long id);
    void deleteById(Long id);
    List<Plant> getAllPlants();
}
