package ${basePackage}.${entity.module}.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ${basePackage}.${entity.module}.${entity.name};
import ${basePackage}.${entity.module}.dtos.${entity.name}DTO;

@Mapper(componentModel = "spring")
public interface ${entity.name}Mapper {

    <#list entity.relations as rel>
    <#if rel.dto?? && rel.dto.idField??>
    @Mapping(source = "${rel.name}.id", target = "${rel.dto.idField}")
    </#if>
    </#list>
    ${entity.name}DTO toDTO(${entity.name} entity);

    <#list entity.relations as rel>
    <#if rel.dto?? && rel.dto.idField??>
    @Mapping(source = "${rel.dto.idField}", target = "${rel.name}.id")
    </#if>
    </#list>
    ${entity.name} toEntity(${entity.name}DTO dto);
}
