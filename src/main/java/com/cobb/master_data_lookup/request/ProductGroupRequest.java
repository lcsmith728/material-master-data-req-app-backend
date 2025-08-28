package com.cobb.master_data_lookup.request;

import com.cobb.master_data_lookup.model.GenerationLevel;
import com.cobb.master_data_lookup.model.ProductGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductGroupRequest {

    private String code;
    private String name;
    private Long parentGroupId;
    private Long generationLevelId;
}
