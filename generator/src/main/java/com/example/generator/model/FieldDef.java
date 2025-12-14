package com.example.generator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FieldDef {
    private String name;
    private String type;
    private String column;
    private Integer length;
    private Boolean nullable;
    private DtoRules dto;

    @JsonProperty("default")
    private DefaultDef defaultValue;

    public static class DefaultDef {
        public Boolean now;
        public String value;
    }

    public static class DtoRules {
        public Boolean notBlank;
        public Boolean notNull;
        public Integer size;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public String getColumn() { return column; }
    public Integer getLength() { return length; }
    public Boolean getNullable() { return nullable; }
    public DtoRules getDto() { return dto; }
    public DefaultDef getDefault() {return defaultValue;}

    // Convenience helpers for templates
    public boolean hasDefault() { return defaultValue != null; }
    public Boolean defaultNow() { return defaultValue != null ? defaultValue.now : null; }
    public String defaultValueAsString() { return defaultValue != null ? defaultValue.value : null; }
    public boolean isDefaultNow() { return defaultValue != null && Boolean.TRUE.equals(defaultValue.now); }
}
