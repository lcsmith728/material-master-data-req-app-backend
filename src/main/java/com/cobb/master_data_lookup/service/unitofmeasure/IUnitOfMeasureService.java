package com.cobb.master_data_lookup.service.unitofmeasure;

import com.cobb.master_data_lookup.model.UnitOfMeasure;
import com.cobb.master_data_lookup.request.UnitOfMeasureRequest;

import java.util.List;

public interface IUnitOfMeasureService {

    UnitOfMeasure create(UnitOfMeasureRequest request);
    UnitOfMeasure getUnitOfMeasureById(Long id);
    UnitOfMeasure update(UnitOfMeasureRequest request, Long id);
    void deleteById(Long id);
    List<UnitOfMeasure> getAllUnitOfMeasures();
}
