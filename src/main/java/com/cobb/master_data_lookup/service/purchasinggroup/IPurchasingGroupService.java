package com.cobb.master_data_lookup.service.purchasinggroup;

import com.cobb.master_data_lookup.model.PurchasingGroup;
import com.cobb.master_data_lookup.request.MasterDataRequest;

import java.util.List;

public interface IPurchasingGroupService {

    PurchasingGroup create(MasterDataRequest request);
    PurchasingGroup getPurchaseGroupById(Long id);
    PurchasingGroup update(MasterDataRequest request, Long id);
    void deleteById(Long id);
    List<PurchasingGroup> getAllPurchasingGroups();
}
