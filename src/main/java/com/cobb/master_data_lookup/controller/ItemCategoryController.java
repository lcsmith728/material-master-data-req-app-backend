package com.cobb.master_data_lookup.controller;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.ItemCategory;
import com.cobb.master_data_lookup.request.MasterDataRequest;
import com.cobb.master_data_lookup.response.ApiResponse;
import com.cobb.master_data_lookup.service.itemcategory.IItemCategoryService;
import com.cobb.master_data_lookup.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
@RequestMapping(UrlMapping.ITEM_CATEGORY)
public class ItemCategoryController {

    private final IItemCategoryService itemCategoryService;

    @RequestMapping(UrlMapping.ITEM_CATEGORY_CREATE)
    public ResponseEntity<ApiResponse> create(@RequestBody MasterDataRequest request) {
        try {
            ItemCategory newItemCategory = itemCategoryService.createItemCategory(request);
            return ResponseEntity.status(201).body(new ApiResponse("Item category created successfully", newItemCategory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.ITEM_CATEGORY_GET_BY_ID)
    public ResponseEntity<ApiResponse> getItemCategoryById(@PathVariable Long id) {
        try {
            ItemCategory itemCategory = itemCategoryService.getItemCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Item category retrieved successfully", itemCategory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.ITEM_CATEGORY_UPDATE)
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody MasterDataRequest request) {
        try {
            ItemCategory itemCategory = itemCategoryService.updateItemCategory(request, id);
            return ResponseEntity.ok(new ApiResponse("Item category updated successfully", itemCategory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping(UrlMapping.ITEM_CATEGORY_DELETE)
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        try {
            itemCategoryService.deleteItemCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Item category deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @RequestMapping(UrlMapping.ITEM_CATEGORY_GET_ALL)
    public ResponseEntity<ApiResponse> getAllItemCategories() {
        try {
            return ResponseEntity.ok(new ApiResponse("Item categories retrieved successfully", itemCategoryService.getAllItemCategories()));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
