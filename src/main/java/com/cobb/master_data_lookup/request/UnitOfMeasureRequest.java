package com.cobb.master_data_lookup.request;

import com.cobb.master_data_lookup.model.UnitOfMeasure;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnitOfMeasureRequest extends MasterDataRequest {

    private String uomQualifier;

    public UnitOfMeasureRequest(String name, String nameDescription, String uomQualifier) {
        super(name, nameDescription);
        this.uomQualifier = uomQualifier;
    }
}
