package ${basePackage}.${entity.module}.controllers;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import ${basePackage}.${entity.module}.dtos.${entity.name}DTO;
import ${basePackage}.${entity.module}.services.${entity.name}Service;
import ${basePackage}.utils.UriBuilderUtil;

@RestController
@RequestMapping("/api/${entity.module}")
@RequiredArgsConstructor
public class ${entity.name}Controller {

    private final ${entity.name}Service service;
    private final UriBuilderUtil uriBuilderUtil;

    @GetMapping
    public ResponseEntity<Page<${entity.name}DTO>> findAll(
            @PageableDefault Pageable pageable) {

        Page<${entity.name}DTO> page = service.findAll(pageable);
        if (page.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<${entity.name}DTO> findById(@PathVariable Long id) {
        return service.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(
            @Valid @RequestBody ${entity.name}DTO dto,
            UriComponentsBuilder ucb) {

        ${entity.name}DTO saved = service.create(dto);
        URI location = uriBuilderUtil.build${entity.name?cap_first}Uri(saved.getId(), ucb);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @Valid @RequestBody ${entity.name}DTO dto) {

        service.update(id, dto);
        return ResponseEntity.noContent().build();
    }

        <#list entity.relations as rel>
            <#if rel.name?lower_case == "estado">
        @PutMapping("/{id}/estado/{estadoId}")
        public ResponseEntity<Void> updateEstado(
                        @PathVariable Long id,
                        @PathVariable Long estadoId) {

                service.updateEstado(id, estadoId);
                return ResponseEntity.noContent().build();
        }
            </#if>
        </#list>

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
