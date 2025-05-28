package com.cobb.master_data_lookup.service.mrptype;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.MrpType;
import com.cobb.master_data_lookup.repository.MrpTypeRepository;
import com.cobb.master_data_lookup.request.MasterDataRequest;
import com.cobb.master_data_lookup.utils.AppMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MrpTypeService implements IMrpTypeService {

    private final MrpTypeRepository mrpTypeRepository;

    @Override
    public MrpType createMrpType(MasterDataRequest request) {
        MrpType mrpType = new MrpType();
        mrpType.setMrpType(request.getName());
        mrpType.setMrpTypeDescription(request.getNameDescription());
        return mrpTypeRepository.save(mrpType);
    }

    @Override
    public MrpType getMrpTypeById(Long id) {
        return mrpTypeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(AppMessages.NOT_FOUND)
        );
    }

    @Override
    public MrpType updateMrpType(MasterDataRequest request, Long id) {
        MrpType mrpType = getMrpTypeById(id);
        mrpType.setMrpType(request.getName());
        mrpType.setMrpTypeDescription(request.getNameDescription());
        return mrpTypeRepository.save(mrpType);
    }

    @Override
    public void deleteMrpTypeById(Long id) {

        if (mrpTypeRepository.existsById(id)) {
            mrpTypeRepository.deleteById(id);
        }
    }

    @Override
    public List<MrpType> getAllMrpTypes() {
        return mrpTypeRepository.findAll();
    }
}
