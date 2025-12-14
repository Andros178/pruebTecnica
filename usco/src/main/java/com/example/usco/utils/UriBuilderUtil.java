package com.example.usco.utils;

import java.net.URI;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UriBuilderUtil {

    public URI buildUsuarioUri(Long id, UriComponentsBuilder ucb) {
        return ucb
            .path("/api/usuario/{id}")
            .buildAndExpand(id)
            .toUri();
    }

    public URI buildPersonaUri(Long id, UriComponentsBuilder ucb) {
        return ucb
            .path("/api/persona/{id}")
            .buildAndExpand(id)
            .toUri();
    }

    public URI buildRolUri(Long id, UriComponentsBuilder ucb) {
        return ucb
            .path("/api/rol/{id}")
            .buildAndExpand(id)
            .toUri();
    }

    public URI buildUsuarioRolUri(Long id, UriComponentsBuilder ucb) {
        return ucb 
            .path("/api/usuario_rol/{id}")
            .buildAndExpand(id)
            .toUri();
    }

    public URI buildEstadoUri(Long id, UriComponentsBuilder ucb) {
        return ucb
            .path("/api/estado/{id}")
            .buildAndExpand(id)
            .toUri();
    }

    public URI buildTipoIdentificacionUri(Long id, UriComponentsBuilder ucb) {
        return ucb
            .path("/api/tipo_identificacion/{id}")
            .buildAndExpand(id)
            .toUri();
    }

    public URI buildTipoTramiteUri(Long id, UriComponentsBuilder ucb) {
        return ucb
            .path("/api/tipo_tramite/{id}")
            .buildAndExpand(id)
            .toUri();
    }

    public URI buildTramiteUri(Long id, UriComponentsBuilder ucb) {
        return ucb
            .path("/api/tramite/{id}")
            .buildAndExpand(id)
            .toUri();
    }

    public URI buildArchivoAdjuntoUri(Long id, UriComponentsBuilder ucb) {
        return ucb
            .path("/api/archivo_adjunto/{id}")
            .buildAndExpand(id)
            .toUri();
    }

}
