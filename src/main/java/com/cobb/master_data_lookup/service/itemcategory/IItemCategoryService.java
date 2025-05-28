package com.cobb.master_data_lookup.service.itemcategory;

import com.cobb.master_data_lookup.model.ItemCategory;
import com.cobb.master_data_lookup.request.MasterDataRequest;

import java.util.List;

public interface IItemCategoryService {

    ItemCategory createItemCategory(MasterDataRequest request);
    ItemCategory getItemCategoryById(Long id);
    ItemCategory updateItemCategory(MasterDataRequest request, Long id);
    void deleteItemCategoryById(Long id);
    List<ItemCategory> getAllItemCategories();
}
