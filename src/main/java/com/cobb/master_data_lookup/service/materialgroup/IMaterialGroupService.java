package com.cobb.master_data_lookup.service.materialgroup;

import com.cobb.master_data_lookup.model.MaterialGroup;
import com.cobb.master_data_lookup.request.MasterDataRequest;

import java.util.List;

public interface IMaterialGroupService {
    MaterialGroup createMaterialGroup(MasterDataRequest request);
    MaterialGroup getMaterialGroupById(Long id);
    MaterialGroup updateMaterialGroup(MasterDataRequest request, Long id);
    void deleteMaterialGroupById(Long id);
    List<MaterialGroup> getAllMaterialGroups();
}
