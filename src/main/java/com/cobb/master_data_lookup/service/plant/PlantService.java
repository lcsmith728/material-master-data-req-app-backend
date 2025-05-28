package com.cobb.master_data_lookup.service.plant;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.Plant;
import com.cobb.master_data_lookup.repository.PlantRepository;
import com.cobb.master_data_lookup.request.PlantRequest;
import com.cobb.master_data_lookup.utils.AppMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlantService implements IPlantService {

    private final PlantRepository plantRepository;

    @Override
    public Plant create(PlantRequest request) {
        Plant plant = new Plant();
        plant.setPlant(request.getName());
        plant.setName1(request.getNameDescription());
        plant.setName2(request.getName2());
        return plantRepository.save(plant);
    }

    @Override
    public Plant getPlantById(Long id) {
        return plantRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(AppMessages.NOT_FOUND)
        );
    }

    @Override
    public Plant update(PlantRequest request, Long id) {
        Plant plant = getPlantById(id);
        plant.setPlant(request.getName());
        plant.setName1(request.getNameDescription());
        plant.setName2(request.getName2());
        return plantRepository.save(plant);
    }

    @Override
    public void deleteById(Long id) {
        if (plantRepository.existsById(id)) {
            plantRepository.deleteById(id);
        }
    }

    @Override
    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }
}
