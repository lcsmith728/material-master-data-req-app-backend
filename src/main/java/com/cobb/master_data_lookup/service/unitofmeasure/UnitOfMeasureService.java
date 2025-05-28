package com.cobb.master_data_lookup.service.unitofmeasure;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.UnitOfMeasure;
import com.cobb.master_data_lookup.repository.UnitOfMeasureRepository;
import com.cobb.master_data_lookup.request.UnitOfMeasureRequest;
import com.cobb.master_data_lookup.utils.AppMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UnitOfMeasureService implements IUnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Override
    public UnitOfMeasure create(UnitOfMeasureRequest request) {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setUom(request.getName());
        unitOfMeasure.setUomDescription(request.getNameDescription());
        unitOfMeasure.setUomQualifier(request.getUomQualifier());
        return unitOfMeasureRepository.save(unitOfMeasure);
    }

    @Override
    public UnitOfMeasure getUnitOfMeasureById(Long id) {
        return unitOfMeasureRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(AppMessages.NOT_FOUND)
        );
    }

    @Override
    public UnitOfMeasure update(UnitOfMeasureRequest request, Long id) {
        UnitOfMeasure unitOfMeasure = getUnitOfMeasureById(id);
        unitOfMeasure.setUom(request.getName());
        unitOfMeasure.setUomDescription(request.getNameDescription());
        unitOfMeasure.setUomQualifier(request.getUomQualifier());
        return unitOfMeasureRepository.save(unitOfMeasure);
    }

    @Override
    public void deleteById(Long id) {
        if (unitOfMeasureRepository.existsById(id)) {
            unitOfMeasureRepository.deleteById(id);
        }
    }

    @Override
    public List<UnitOfMeasure> getAllUnitOfMeasures() {
        return unitOfMeasureRepository.findAll();
    }
}
