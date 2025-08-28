package com.cobb.master_data_lookup.controller;

import com.cobb.master_data_lookup.dto.CatalogTreeDto;
import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.GenerationLevel;
import com.cobb.master_data_lookup.model.Product;
import com.cobb.master_data_lookup.model.ProductGroup;
import com.cobb.master_data_lookup.model.ProductType;
import com.cobb.master_data_lookup.request.ProductGroupRequest;
import com.cobb.master_data_lookup.request.ProductRequest;
import com.cobb.master_data_lookup.response.ApiResponse;
import com.cobb.master_data_lookup.service.CatalogTreeService;
import com.cobb.master_data_lookup.service.producthierarchy.ProductHierarchyService;
import com.cobb.master_data_lookup.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cobb.master_data_lookup.exception.ResourceNotFoundException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
@RequestMapping(UrlMapping.PRODUCT_HIERARCHY)
public class ProductHierarchyController {

    @Autowired
    private final ProductHierarchyService productHierarchyService;

    @Autowired
    private final CatalogTreeService treeService;

    @GetMapping(UrlMapping.GET_ALL_GENERATION_LEVELS)
    public ResponseEntity<ApiResponse> getAllLevels() {
        try {
            List<GenerationLevel> levels = productHierarchyService.getAllGenerationLevels();
            return ResponseEntity.ok(new ApiResponse("Generation levels retrieved successfully", levels));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping(UrlMapping.CREATE_GENERATION_LEVEL)
    public ResponseEntity<ApiResponse> createGenerationLevel(@RequestBody GenerationLevel generationLevel) {
        try {
            GenerationLevel newGenerationLevel = productHierarchyService.createGenerationLevel(generationLevel);
            return ResponseEntity.status(201).body(new ApiResponse("Generation level created successfully", newGenerationLevel));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping(UrlMapping.GET_ALL_PRODUCT_TYPES)
    public ResponseEntity<ApiResponse> getAllProductTypes() {
        try {
            List<ProductType> productTypes = productHierarchyService.getAllProductTypes();
            return ResponseEntity.ok(new ApiResponse("Product types retrieved successfully", productTypes));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping(UrlMapping.CREATE_PRODUCT_TYPE)
    public ResponseEntity<ApiResponse> createProductType(@RequestBody ProductType productType) {
        try {
            ProductType newProductType = productHierarchyService.createProductType(productType);
            return ResponseEntity.status(201).body(new ApiResponse("Product type created successfully", newProductType));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping(UrlMapping.GET_ALL_GROUPS)
    public ResponseEntity<ApiResponse> getAllGroups() {
        try {
            List<ProductGroup> groups = productHierarchyService.getAllGroups();
            return ResponseEntity.ok(new ApiResponse("Groups retrieved successfully", groups));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping(UrlMapping.CREATE_GROUP)
    public ResponseEntity<ApiResponse> createGroup(@RequestBody ProductGroupRequest productGroupRequest) {
        try {
            ProductGroup newGroup = productHierarchyService.createGroup(productGroupRequest);
            return ResponseEntity.status(201).body(new ApiResponse("Group created successfully", newGroup));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping(UrlMapping.GET_ALL_PRODUCTS)
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            List<Product> products = productHierarchyService.getAllProducts();
            return ResponseEntity.ok(new ApiResponse("Products retrieved successfully", products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping(UrlMapping.CREATE_PRODUCT)
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductRequest productRequest) {
        try {
            Product newProduct = productHierarchyService.createProduct(productRequest);
            return ResponseEntity.status(201).body(new ApiResponse("Product created successfully", newProduct));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping(UrlMapping.DELETE_PRODUCT)
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable String code) {
        try {
            productHierarchyService.deleteProduct(code);
            return ResponseEntity.ok(new ApiResponse("Product deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping(UrlMapping.UPDATE_PRODUCT)
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable String code, @RequestBody Product product) {
        try {
            Product updatedProduct = productHierarchyService.updateProduct(code, product);
            return ResponseEntity.ok(new ApiResponse("Product updated successfully", updatedProduct));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping(UrlMapping.PRODUCT_TREE)
    public ResponseEntity<ApiResponse> getProductHierarchyTree() {
        try {
            CatalogTreeDto tree = treeService.getTree();
            return ResponseEntity.ok(new ApiResponse("Tree retrieve successfully", tree));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
