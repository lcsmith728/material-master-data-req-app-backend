package com.cobb.master_data_lookup.service.materialtype;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.MaterialType;
import com.cobb.master_data_lookup.repository.MaterialTypeRepository;
import com.cobb.master_data_lookup.request.MasterDataRequest;
import com.cobb.master_data_lookup.utils.AppMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialTypeService implements IMaterialTypeService {

    private final MaterialTypeRepository materialTypeRepository;

    @Override
    public MaterialType createMaterialType(MasterDataRequest request) {
        MaterialType materialType = new MaterialType();
        materialType.setMaterialType(request.getName());
        materialType.setMaterialTypeDescription(request.getNameDescription());
        return materialTypeRepository.save(materialType);
    }

    @Override
    public MaterialType getMaterialTypeById(Long id) {
        return materialTypeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(AppMessages.NOT_FOUND)
        );
    }

    @Override
    public MaterialType updateMaterialType(MasterDataRequest request, Long id) {

        MaterialType materialType = getMaterialTypeById(id);
        materialType.setMaterialType(request.getName());
        materialType.setMaterialTypeDescription(request.getNameDescription());
        return materialTypeRepository.save(materialType);
    }

    @Override
    public void deleteMaterialTypeById(Long id) {

        if (materialTypeRepository.existsById(id)) {
            materialTypeRepository.deleteById(id);
        }
    }

    @Override
    public List<MaterialType> getAllMaterialTypes() {
        return materialTypeRepository.findAll();
    }
}
