package ${basePackage}.${entity.module}.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ${entity.name}DTO {

    private Long id;

    <#list entity.fields as field>
    private ${field.type} ${field.name};
    </#list>

    <#list entity.relations as rel>
    <#if rel.dto?? && rel.dto.idField??>
    private Long ${rel.dto.idField};
    </#if>
    </#list>
}
