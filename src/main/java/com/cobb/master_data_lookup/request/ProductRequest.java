package com.cobb.master_data_lookup.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductRequest {

    private String code;
    private String name;
    private Long productGroupId;
    private Long productTypeId;
}
