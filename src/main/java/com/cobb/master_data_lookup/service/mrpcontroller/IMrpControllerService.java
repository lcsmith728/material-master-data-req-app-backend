package com.cobb.master_data_lookup.service.mrpcontroller;

import com.cobb.master_data_lookup.model.MrpController;
import com.cobb.master_data_lookup.request.MasterDataRequest;

import java.util.List;

public interface IMrpControllerService {

    MrpController create(MasterDataRequest request);
    MrpController getMrpControllerById(Long id);
    MrpController update(MasterDataRequest request, Long id);
    void deleteById(Long id);
    List<MrpController> getAllMrpControllers();
}
