package ${basePackage}.${entity.module}.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import ${basePackage}.${entity.module}.dtos.${entity.name}DTO;
import ${basePackage}.${entity.module}.mappers.${entity.name}Mapper;
import ${basePackage}.${entity.module}.repositories.${entity.name}Repository;

@Service
@RequiredArgsConstructor
public class ${entity.name}Service {

    private final ${entity.name}Repository repository;
    private final ${entity.name}Mapper mapper;

    public Page<${entity.name}DTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    public Optional<${entity.name}DTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public ${entity.name}DTO create(${entity.name}DTO dto) {
        dto.setId(null);
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public void update(Long id, ${entity.name}DTO dto) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("${entity.name} no encontrado"));

        dto.setId(id);
        repository.save(mapper.toEntity(dto));
    }

    public void delete(Long id) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("${entity.name} no encontrado"));

        repository.deleteById(id);
    }

    <#list entity.relations as rel>
      <#if rel.name?lower_case == "estado">
    public void updateEstado(Long id, Long estadoId) {
        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("${entity.name} no encontrado"));

        ${basePackage}.estado.Estado nuevoEstado = new ${basePackage}.estado.Estado();
        nuevoEstado.setId(estadoId);
        entity.setEstado(nuevoEstado);

        repository.save(entity);
    }
      </#if>
    </#list>
}
