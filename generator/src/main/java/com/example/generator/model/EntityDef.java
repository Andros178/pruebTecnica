package com.example.generator.model;

import java.util.List;

public class EntityDef {

    private String module;
    private String name;
    private String table;
    private String schema;

    private IdDef id;
    private List<FieldDef> fields;
    private List<RelationDef> relations;
    private RepositoryDef repository;

    public String getModule() {return module;}
    public String getName() {return name;}
    public String getTable() {return table;}
    public String getSchema() {return schema;}

    public IdDef getId() {return id;}

    public List<FieldDef> getFields() {return fields;}

    public List<RelationDef> getRelations() {return relations;}
    public RepositoryDef getRepository() {return repository;}
}
