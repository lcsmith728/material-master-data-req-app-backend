package com.cobb.master_data_lookup.service.mrpcontroller;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.MrpController;
import com.cobb.master_data_lookup.repository.MrpControllerRepository;
import com.cobb.master_data_lookup.request.MasterDataRequest;
import com.cobb.master_data_lookup.utils.AppMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MrpControllerService implements IMrpControllerService{

    private final MrpControllerRepository mrpControllerRepository;

    @Override
    public MrpController create(MasterDataRequest request) {
        MrpController mrpController = new MrpController();
        mrpController.setMrpController(request.getName());
        mrpController.setMrpControllerDescription(request.getNameDescription());
        return mrpControllerRepository.save(mrpController);
    }

    @Override
    public MrpController getMrpControllerById(Long id) {
        return mrpControllerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(AppMessages.NOT_FOUND)
        );
    }

    @Override
    public MrpController update(MasterDataRequest request, Long id) {
        MrpController mrpController = getMrpControllerById(id);
        mrpController.setMrpController(request.getName());
        mrpController.setMrpControllerDescription(request.getNameDescription());
        return mrpControllerRepository.save(mrpController);
    }

    @Override
    public void deleteById(Long id) {
        if (mrpControllerRepository.existsById(id)) {
            mrpControllerRepository.deleteById(id);
        }
    }

    @Override
    public List<MrpController> getAllMrpControllers() {
        return mrpControllerRepository.findAll();
    }
}
