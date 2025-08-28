package com.cobb.master_data_lookup.service;

import com.cobb.master_data_lookup.dto.CatalogTreeDto;
import com.cobb.master_data_lookup.model.GenerationLevel;
import com.cobb.master_data_lookup.model.Product;
import com.cobb.master_data_lookup.model.ProductGroup;
import com.cobb.master_data_lookup.model.ProductType;
import com.cobb.master_data_lookup.repository.GenerationLevelRepository;
import com.cobb.master_data_lookup.repository.ProductGroupRepository;
import com.cobb.master_data_lookup.repository.ProductRepository;
import com.cobb.master_data_lookup.repository.ProductTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CatalogTreeService {

    @Autowired
    private final GenerationLevelRepository generationRepo;
    @Autowired
    private final ProductGroupRepository groupRepo;
    @Autowired
    private final ProductTypeRepository typeRepo;
    @Autowired
    private final ProductRepository productRepo;

    @Transactional(readOnly = true)
    public CatalogTreeDto getTree() {

        // 1. fetch everything in 4 queries
        List<GenerationLevel> generations = generationRepo.findAll();
        List<ProductGroup> groups = groupRepo.findAll();
        List<Product> products = productRepo.findAll();
        List<ProductType> types = typeRepo.findAll();

        // Partition groups by role using their codes:
        // Lines: 200 / 210 / 900, Sexes: 300 / 310 / 900
        Set<String> LINE_CODES = Set.of("200", "210", "900");
        Set<String> SEX_CODES  = Set.of("300", "310", "900");

        // 2. build 2 quick lookups
        Map<Long, GenerationLevel> genById = generations.stream()
                .collect(Collectors.toMap(GenerationLevel::getId, g -> g));

        // linesByGenId: groups with code in LINE_CODES and generationLevel != null
        Map<Long, List<ProductGroup>> linesByGenId = groups.stream()
                .filter(g -> g.getGenerationLevel() != null && LINE_CODES.contains(safeStr(g.getCode())))
                .collect(Collectors.groupingBy(g -> g.getGenerationLevel().getId()));

        // sexesByLineId: groups with code in SEX_CODES and parent != null
        Map<Long, List<ProductGroup>> sexesByLineId = groups.stream()
                .filter(g -> g.getParentGroup() != null && SEX_CODES.contains(safeStr(g.getCode())))
                .collect(Collectors.groupingBy(g -> g.getParentGroup().getId()));

        // productsBySexId: products grouped by their group's id (sex node)
        Map<Long, List<Product>> productsBySexId = products.stream()
                .filter(p -> p.getProductGroup() != null)
                .collect(Collectors.groupingBy(p -> p.getProductGroup().getId()));

        // index product types by code for quick mapping
        Map<String, ProductType> productTypeByCode = types.stream()
                .collect(Collectors.toMap(ProductType::getCode, pt -> pt, (a,b) -> a));

        CatalogTreeDto dto = new CatalogTreeDto();
        dto.generations = generations.stream()
                .sorted(Comparator.comparing(GenerationLevel::getCode, Comparator.nullsLast(String::compareTo)))
                .map(gen -> {
                    CatalogTreeDto.GenerationNode genNode = new CatalogTreeDto.GenerationNode();
                    genNode.id = gen.getId();
                    genNode.code = gen.getCode();
                    genNode.name = gen.getName();

                    List<ProductGroup> lineGroups = linesByGenId.getOrDefault(gen.getId(), List.of());
                    genNode.lines = lineGroups.stream()
                            .sorted(Comparator.comparing(ProductGroup::getCode, Comparator.nullsLast(String::compareTo)))
                            .map(line -> {
                                CatalogTreeDto.LineNode lineNode = new CatalogTreeDto.LineNode();
                                lineNode.id = line.getId();
                                lineNode.code = line.getCode();
                                lineNode.name = line.getName();

                                List<ProductGroup> sexGroups = sexesByLineId.getOrDefault(line.getId(), List.of());
                                lineNode.sexes = sexGroups.stream()
                                        .sorted(Comparator.comparing(ProductGroup::getCode, Comparator.nullsLast(String::compareTo)))
                                        .map(sex -> {
                                            CatalogTreeDto.SexNode sexNode = new CatalogTreeDto.SexNode();
                                            sexNode.id = sex.getId();
                                            sexNode.code = sex.getCode();
                                            sexNode.name = sex.getName();

                                            // group products under sex node by product "name" to form "Type"
                                            List<Product> prods = productsBySexId.getOrDefault(sex.getId(), List.of());
                                            Map<String, List<Product>> byTypeName = prods.stream()
                                                    .collect(Collectors.groupingBy(p -> safeStr(p.getName())));

                                            sexNode.types = byTypeName.entrySet().stream()
                                                    .sorted(Map.Entry.comparingByKey())
                                                    .map(entry -> {
                                                        String typeKey = entry.getKey();
                                                        Long typeId = entry.getValue().get(0).getId();
                                                        String typeCode = entry.getValue().get(0).getCode();
                                                        CatalogTreeDto.TypeNode typeNode = new CatalogTreeDto.TypeNode();
                                                        typeNode.id = typeId;
                                                        typeNode.code = typeCode;
                                                        typeNode.name = typeKey;

                                                        // Distinct Chick/Egg/NA from Product.getType() (ProductType entity)
                                                        Collection<ProductType> typeSet = entry.getValue().stream()
                                                                .map(Product::getProductType)
                                                                .filter(Objects::nonNull)
                                                                // uniqueness by ProductType.code
                                                                .collect(Collectors.toMap(ProductType::getCode, t -> t, (a,b) -> a))
                                                                .values();

                                                        typeNode.chickEgg = typeSet.stream()
                                                                .sorted(Comparator.comparing(ProductType::getCode))
                                                                .map(pt -> {
                                                                    CatalogTreeDto.ChickEggNode ce = new CatalogTreeDto.ChickEggNode();
                                                                    ce.id = pt.getId();
                                                                    ce.code = pt.getCode();
                                                                    ce.name = pt.getName();
                                                                    return ce;
                                                                })
                                                                .collect(Collectors.toList());

                                                        return typeNode;
                                                    })
                                                    .collect(Collectors.toList());

                                            return sexNode;
                                        })
                                        .collect(Collectors.toList());

                                return lineNode;
                            })
                            .collect(Collectors.toList());

                    return genNode;
                })
                .collect(Collectors.toList());

        return dto;
    }

    private static String safeStr(String s) { return s == null ? "" : s; }
}
