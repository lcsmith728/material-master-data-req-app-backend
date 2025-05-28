package com.cobb.master_data_lookup.service.purchasinggroup;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.PurchasingGroup;
import com.cobb.master_data_lookup.repository.PurchasingGroupRepository;
import com.cobb.master_data_lookup.request.MasterDataRequest;
import com.cobb.master_data_lookup.utils.AppMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PurchasingGroupService implements IPurchasingGroupService {

    private final PurchasingGroupRepository purchasingGroupRepository;

    @Override
    public PurchasingGroup create(MasterDataRequest request) {
        PurchasingGroup purchasingGroup = new PurchasingGroup();
        purchasingGroup.setPurchasingGroup(request.getName());
        purchasingGroup.setPurchasingGroupDescription(request.getNameDescription());
        return purchasingGroupRepository.save(purchasingGroup);
    }

    @Override
    public PurchasingGroup getPurchaseGroupById(Long id) {
        return purchasingGroupRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(AppMessages.NOT_FOUND)
        );
    }

    @Override
    public PurchasingGroup update(MasterDataRequest request, Long id) {
        PurchasingGroup purchasingGroup = getPurchaseGroupById(id);
        purchasingGroup.setPurchasingGroup(request.getName());
        purchasingGroup.setPurchasingGroupDescription(request.getNameDescription());
        return purchasingGroupRepository.save(purchasingGroup);
    }

    @Override
    public void deleteById(Long id) {
        if (purchasingGroupRepository.existsById(id)) {
            purchasingGroupRepository.deleteById(id);
        }
    }

    @Override
    public List<PurchasingGroup> getAllPurchasingGroups() {
        return purchasingGroupRepository.findAll();
    }
}
