package com.cobb.master_data_lookup.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlantRequest extends MasterDataRequest {
   private String name2;

   public PlantRequest(String name, String nameDescription, String name2) {
       super(name, nameDescription);
       this.name2 = name2;
   }
}
