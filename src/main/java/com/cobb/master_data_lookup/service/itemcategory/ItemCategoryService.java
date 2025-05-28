package com.cobb.master_data_lookup.service.itemcategory;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.ItemCategory;
import com.cobb.master_data_lookup.repository.ItemCategoryRepository;
import com.cobb.master_data_lookup.request.MasterDataRequest;
import com.cobb.master_data_lookup.utils.AppMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemCategoryService implements IItemCategoryService {

    private final ItemCategoryRepository itemCategoryRepository;

    @Override
    public ItemCategory createItemCategory(MasterDataRequest request) {

        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setItemCategory(request.getName());
        itemCategory.setItemCategoryDescription(request.getNameDescription());

        return itemCategoryRepository.save(itemCategory);
    }

    @Override
    public ItemCategory getItemCategoryById(Long id) {

        return itemCategoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(AppMessages.NOT_FOUND)
        );
    }

    @Override
    public ItemCategory updateItemCategory(MasterDataRequest request, Long id) {
        if (itemCategoryRepository.existsById(id)) {

            ItemCategory itemCategory = getItemCategoryById(id);
            itemCategory.setItemCategory(request.getName());
            itemCategory.setItemCategoryDescription(request.getNameDescription());
            return itemCategoryRepository.save(itemCategory);
        }
        throw new ResourceNotFoundException(AppMessages.NOT_FOUND);
    }

    @Override
    public void deleteItemCategoryById(Long id) {

        if (itemCategoryRepository.existsById(id)) {
            itemCategoryRepository.deleteById(id);
        }
    }

    @Override
    public List<ItemCategory> getAllItemCategories() {
        return itemCategoryRepository.findAll();
    }
}
