package com.cobb.master_data_lookup.service.materialtype;

import com.cobb.master_data_lookup.model.MaterialType;
import com.cobb.master_data_lookup.request.MasterDataRequest;

import java.util.List;

public interface IMaterialTypeService {

    MaterialType createMaterialType(MasterDataRequest request);
    MaterialType getMaterialTypeById(Long id);
    MaterialType updateMaterialType(MasterDataRequest request, Long id);
    void deleteMaterialTypeById(Long id);
    List<MaterialType> getAllMaterialTypes();
}
