package com.cobb.master_data_lookup.service.mrptype;

import com.cobb.master_data_lookup.model.MrpType;
import com.cobb.master_data_lookup.request.MasterDataRequest;

import java.util.List;

public interface IMrpTypeService {

    MrpType createMrpType(MasterDataRequest request);
    MrpType getMrpTypeById(Long id);
    MrpType updateMrpType(MasterDataRequest request, Long id);
    void deleteMrpTypeById(Long id);
    List<MrpType> getAllMrpTypes();
}
