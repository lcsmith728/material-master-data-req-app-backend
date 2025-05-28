package com.cobb.master_data_lookup.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MasterDataRequest {
    private String name;
    private String nameDescription;
}
