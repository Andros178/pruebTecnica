package com.example.generator;

import com.example.generator.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.*;

import java.io.*;
import java.nio.file.*;

public class GeneratorApp {

    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        DomainModel model = mapper.readValue(new File("modelo.json"), DomainModel.class);

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setClassLoaderForTemplateLoading(
            GeneratorApp.class.getClassLoader(),
            "/com/example/generator/template"
        );
        generateUriBuilderUtil(cfg, model);
        for (EntityDef entity : model.getEntities()) {
            System.out.println("Generating entity: " + entity.getName());
            if (entity.getFields() != null) {
                for (var f : entity.getFields()) {
                    System.out.println("  field: " + f.getName() + " default=" + f.getDefault());
                }
            }
            if (entity.getRelations() != null) {
                for (var r : entity.getRelations()) {
                    System.out.println("  relation: " + r.getName() + " default=" + r.getDefault());
                }
            }

            generateEntity(cfg, model, entity);
        }
    }

    private static void generateEntity(
        Configuration cfg,
        DomainModel model,
        EntityDef entity
) throws Exception {

    generateFromTemplate(cfg, model, entity,
            "Entity.java.ftl",
            "",
            "");

    generateFromTemplate(cfg, model, entity,
            "Repository.java.ftl",
            "repositories",
            "Repository");

    generateFromTemplate(cfg, model, entity,
            "Service.java.ftl",
            "services",
            "Service");

    generateFromTemplate(cfg, model, entity,
            "Controller.java.ftl",
            "controllers",
            "Controller");

    generateFromTemplate(cfg, model, entity,
            "Mapper.java.ftl",
            "mappers",
            "Mapper");

    generateFromTemplate(cfg, model, entity,
            "Dto.java.ftl",
            "dtos",
            "DTO");
            
}


    private static void generateFromTemplate(
        Configuration cfg,
        DomainModel model,
        EntityDef entity,
        String templateName,
        String subFolder,
        String suffix
) throws Exception {

    Template template = cfg.getTemplate(templateName);

    var data = new java.util.HashMap<String, Object>();
    data.put("basePackage", model.getBasePackage());
    data.put("entity", entity);

    Path outputDir = Paths.get(
        "output/src/main/java",
        model.getBasePackage().replace(".", "/"),
        entity.getModule(),
        subFolder
    );

    Files.createDirectories(outputDir);

    try (Writer writer = Files.newBufferedWriter(
            outputDir.resolve(entity.getName() + suffix + ".java"))) {
        template.process(data, writer);
    }
}

private static void generateUriBuilderUtil(Configuration cfg, DomainModel model) throws Exception {
    Template template = cfg.getTemplate("UriBuilderUtil.java.ftl");

    var data = new java.util.HashMap<String, Object>();
    data.put("basePackage", model.getBasePackage());
    data.put("entities", model.getEntities());

    Path outputDir = Paths.get(
        "output/src/main/java",
        model.getBasePackage().replace(".", "/"),
        "utils"
    );

    Files.createDirectories(outputDir);

    try (Writer writer = Files.newBufferedWriter(
            outputDir.resolve("UriBuilderUtil.java"))) {
        template.process(data, writer);
    }
}



}
