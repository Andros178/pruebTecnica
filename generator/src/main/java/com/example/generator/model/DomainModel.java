package com.example.generator.model;

import java.util.List;

public class DomainModel {
    private String basePackage;
    private String apiVersion;
    private String projectId;
    private List<EntityDef> entities;

    public String getBasePackage() { return basePackage; }
    public String getApiVersion() { return apiVersion; }
    public String getProjectId() {return projectId;}
    public List<EntityDef> getEntities() { return entities; }
}
