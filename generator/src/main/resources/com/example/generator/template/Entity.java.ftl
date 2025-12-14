package ${basePackage}.${entity.module};

import jakarta.persistence.*;
import lombok.*;
<#assign usesLocalDateTime = false>
<#list entity.fields as field>
  <#if field.type?matches(".*LocalDateTime$")>
    <#assign usesLocalDateTime = true>
  </#if>
</#list>

<#assign hasDefaults = false>
<#list entity.fields as field>
  <#if field.hasDefault()>
    <#assign hasDefaults = true>
  </#if>
</#list>
<#list entity.relations as rel>
  <#if rel.hasDefault()>
    <#assign hasDefaults = true>
  </#if>
</#list>

<#if usesLocalDateTime>
import java.time.LocalDateTime;
</#if>

<#list entity.relations as rel>
import ${basePackage}.${rel.module}.${rel.targetEntity};
</#list>

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "${entity.table}", schema = "${entity.schema}")
public class ${entity.name} {

    @Id
    @SequenceGenerator(
        name = "${entity.id.sequence.name}",
        sequenceName = "${entity.id.sequence.sequenceName}",
        allocationSize = ${entity.id.sequence.allocationSize}
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "${entity.id.sequence.name}")
    @Column(name = "${entity.id.column}")
    private ${entity.id.type} ${entity.id.name};


<#list entity.fields as field>
    @Column(
        name = "${field.column}"
        <#if field.length??>, length = ${field.length}</#if>
        <#if field.nullable??>, nullable = ${field.nullable?c}</#if>
    )
    private ${field.type} ${field.name};

</#list>

<#list entity.relations as rel>

    @ManyToOne(fetch = FetchType.${rel.fetch!"LAZY"})
    <#if rel.joinColumn?? && rel.joinColumn.name??>
    @JoinColumn(
        name = "${rel.joinColumn.name}",
        referencedColumnName = "${rel.joinColumn.referencedColumn!"id"}",
        nullable = ${rel.joinColumn.nullable?c}
    )
    </#if>
    private ${rel.targetEntity} ${rel.name};

</#list>

<#if hasDefaults>

    @PrePersist
    public void prePersist() {

    <#list entity.fields as field>
      <#if field.hasDefault() && field.isDefaultNow()>
      if (this.${field.name} == null) {
        <#if usesLocalDateTime>
        this.${field.name} = LocalDateTime.now();
        <#else>
        this.${field.name} = java.time.LocalDateTime.now();
        </#if>
      }
      </#if>
    </#list>

    <#list entity.relations as rel>
      <#if rel.hasDefault() && rel.hasDefaultId()>
      if (this.${rel.name} == null) {
        this.${rel.name} = new ${rel.targetEntity}();
        this.${rel.name}.setId(${rel.defaultId()}L);
      }
      </#if>
    </#list>

    }

</#if>
}
