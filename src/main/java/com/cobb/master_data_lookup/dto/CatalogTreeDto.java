package com.cobb.master_data_lookup.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CatalogTreeDto {
    public List<GenerationNode> generations;

    public static class GenerationNode {
        public Long id; public String code; public String name;
        public List<LineNode> lines;
    }
    public static class LineNode {
        public Long id; public String code; public String name;
        public List<SexNode> sexes;
    }
    public static class SexNode {
        public Long id; public String code; public String name;
        public List<TypeNode> types;
    }
    public static class TypeNode {
        public Long id;   // we’ll key by product “name” (or switch to ProductKind id if you have it)
        public String code;
        public String name;
        public List<ChickEggNode> chickEgg;
    }
    public static class ChickEggNode {
        public Long id; public String code; public String name;
    }
}
