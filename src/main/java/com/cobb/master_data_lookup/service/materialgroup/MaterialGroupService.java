package com.cobb.master_data_lookup.service.materialgroup;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.MaterialGroup;
import com.cobb.master_data_lookup.repository.MaterialGroupRepository;
import com.cobb.master_data_lookup.request.MasterDataRequest;
import com.cobb.master_data_lookup.utils.AppMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@@RequiredArgsConstructor
public class MaterialGroupService implements IMaterialGroupService {

    private final MaterialGroupRepository materialGroupRepository;

    @Override
    public MaterialGroup createMaterialGroup(MasterDataRequest request) {
        MaterialGroup materialGroup = new MaterialGroup();
        materialGroup.setMaterialGroup(request.getName());
        materialGroup.setMaterialGroupDescription(request.getNameDescription());
        return materialGroupRepository.save(materialGroup);
    }

    @Override
    public MaterialGroup getMaterialGroupById(Long id) {

        return materialGroupRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(AppMessages.NOT_FOUND)
        );
    }

    @Override
    public MaterialGroup updateMaterialGroup(MasterDataRequest request, Long id) {
        MaterialGroup materialGroup = getMaterialGroupById(id);
        materialGroup.setMaterialGroup(request.getName());
        materialGroup.setMaterialGroupDescription(request.getNameDescription());
        return materialGroupRepository.save(materialGroup);
    }

    @Override
    public void deleteMaterialGroupById(Long id) {

        if (materialGroupRepository.existsById(id)) {
            materialGroupRepository.deleteById(id);
        }
    }

    @Override
    public List<MaterialGroup> getAllMaterialGroups() {
        return materialGroupRepository.findAll();
    }
}
