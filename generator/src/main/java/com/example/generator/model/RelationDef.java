package com.example.generator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RelationDef {
    private String name;
    private String type;
    private String targetEntity;
    private String module;
    private String fetch;
    private JoinColumnDef joinColumn;
    private DtoRelation dto;
    @JsonProperty("default")
    private DefaultDef defaultValue;

    public static class DefaultDef{
        public Long id;
    }

    public static class JoinColumnDef {
        public String name;
        public String referencedColumn;
        public Boolean nullable;
    }

    public static class DtoRelation {
        public String idField;
        public Boolean notNull;
    }

    public String getName() { return name; }
    public String getType() { return type;}
    public String getTargetEntity() { return targetEntity; }
    public String getModule() { return module; }
    public String getFetch() { return fetch; }
    public JoinColumnDef getJoinColumn() { return joinColumn; }
    public DtoRelation getDto() { return dto; }
    public DefaultDef getDefault(){return defaultValue;}

    // Convenience helpers for templates
    public boolean hasDefault() { return defaultValue != null; }
    public Long defaultId() { return defaultValue != null ? defaultValue.id : null; }
    public boolean hasDefaultId() { return defaultValue != null && defaultValue.id != null; }

}
