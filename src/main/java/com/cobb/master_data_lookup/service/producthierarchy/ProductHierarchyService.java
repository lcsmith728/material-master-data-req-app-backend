package com.cobb.master_data_lookup.service.producthierarchy;

import com.cobb.master_data_lookup.exception.ResourceNotFoundException;
import com.cobb.master_data_lookup.model.GenerationLevel;
import com.cobb.master_data_lookup.model.Product;
import com.cobb.master_data_lookup.model.ProductGroup;
import com.cobb.master_data_lookup.model.ProductType;
import com.cobb.master_data_lookup.repository.GenerationLevelRepository;
import com.cobb.master_data_lookup.repository.ProductGroupRepository;
import com.cobb.master_data_lookup.repository.ProductRepository;
import com.cobb.master_data_lookup.repository.ProductTypeRepository;
import com.cobb.master_data_lookup.request.ProductGroupRequest;
import com.cobb.master_data_lookup.request.ProductRequest;
import com.cobb.master_data_lookup.utils.AppMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductHierarchyService {

    @Autowired
    private final GenerationLevelRepository generationLevelRepo;
    @Autowired
    private final ProductTypeRepository productTypeRepo;
    @Autowired
    private final ProductGroupRepository productGroupRepo;
    @Autowired
    private final ProductRepository productRepo;


    public List<GenerationLevel> getAllGenerationLevels() {
        return generationLevelRepo.findAll();
    }

    public GenerationLevel createGenerationLevel(GenerationLevel level) {
        return generationLevelRepo.save(level);
    }

    public List<ProductType> getAllProductTypes() {
        return productTypeRepo.findAll();
    }

    public ProductType createProductType(ProductType type) {
        return productTypeRepo.save(type);
    }

    public List<ProductGroup> getAllGroups() {
        return productGroupRepo.findAll();
    }

    public ProductGroup createGroup(ProductGroupRequest groupRequest) {
        // check if parent group exists or is null
        if (groupRequest.getParentGroupId() != null) {
            Optional<ProductGroup> parentGroup = productGroupRepo.findById(groupRequest.getParentGroupId());
        }

        // check if generation level exists or is null (throw exception)
        if (groupRequest.getGenerationLevelId() == null) {
            throw new ResourceNotFoundException(AppMessages.GENERATION_LEVEL_NOT_FOUND);
        }

        // get generation level object
        Optional<GenerationLevel> generationLevel = generationLevelRepo.findById(groupRequest.getGenerationLevelId());
        if (!generationLevel.isPresent()) {
            throw new ResourceNotFoundException(AppMessages.GENERATION_LEVEL_NOT_FOUND);
        }

        // create group object
        ProductGroup group = new ProductGroup();
        group.setCode(groupRequest.getCode());
        group.setName(groupRequest.getName());
        group.setGenerationLevel(generationLevel.get());
        if (groupRequest.getParentGroupId() != null) {
            group.setParentGroup(productGroupRepo.findById(groupRequest.getParentGroupId()).get());
        } else {
            group.setParentGroup(null);
        }

        // populate group object with data from request and get generation level id and parent group id if not null
        return productGroupRepo.save(group);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product createProduct(ProductRequest productRequest) {
        if (productRequest.getProductGroupId() == null || productRequest.getProductTypeId() == null) {
            throw new ResourceNotFoundException(AppMessages.PRODUCT_GROUP_OR_TYPE_NOT_FOUND);
        }
        Product product = new Product();
        product.setCode(productRequest.getCode());
        product.setName(productRequest.getName());
        product.setProductGroup(productGroupRepo.findById(productRequest.getProductGroupId()).get());
        product.setProductType(productTypeRepo.findById(productRequest.getProductTypeId()).get());
        return productRepo.save(product);
    }

    public void deleteProduct(String code) {
        productRepo.deleteById(code);
    }

    public Product updateProduct(String code, Product updated) {
        Product existing = productRepo.findById(code).orElseThrow(
                () -> new ResourceNotFoundException(AppMessages.NOT_FOUND));
        existing.setName(updated.getName());
        existing.setProductGroup(updated.getProductGroup());
        existing.setProductType(updated.getProductType());
        return productRepo.save(existing);
    }
}
