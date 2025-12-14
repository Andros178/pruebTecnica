package ${basePackage}.${entity.module}.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ${basePackage}.${entity.module}.${entity.name};

@Repository
public interface ${entity.name}Repository
        extends JpaRepository<${entity.name}, Long> {
}
