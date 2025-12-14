package ${basePackage}.utils;

import java.net.URI;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UriBuilderUtil {

<#list entities as entity>
    public URI build${entity.name?cap_first}Uri(Long id, UriComponentsBuilder ucb) {
        return ucb
            .path("/api/${entity.table}/{id}")
            .buildAndExpand(id)
            .toUri();
    }

</#list>
}
