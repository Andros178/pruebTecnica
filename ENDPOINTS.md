# API Endpoints — Backend (usco)


---

## Auth

- POST `/api/auth/login`
  - Request: `LoginRequest`
  - Response: `LoginResponse` (JWT)

- POST `/api/auth/register`
  - Request: `RegisterRequest`
  - Response: `LoginResponse`

## Usuario (`/api/usuario`)

- GET `/api/usuario`  
  - Query: `page`, `size` (Pageable)
  - Returns: `Page<UsuarioDTO>`

- GET `/api/usuario/{id}`  
  - Returns: `UsuarioDTO`

- POST `/api/usuario`  
  - Request: `UsuarioDTO`  
  - Response: 201 Created (Location header)

- PUT `/api/usuario/{id}`  
  - Request: `UsuarioDTO`  
  - Response: 204 No Content

- PUT `/api/usuario/{id}/estado/{estadoId}`  
  - Request: none  
  - Response: 204 No Content

- DELETE `/api/usuario/{id}`  
  - Response: 204 No Content

- GET `/api/usuario/{id}/roles`  
  - Returns: `List<RolDTO>`

- GET `/api/usuario/role/{rolNombre}`  
  - Returns: `List<UsuarioDTO>` (users assigned to role)

## Trámite (`/api/tramite`)

- GET `/api/tramite`  
  - Query: `page`, `size`  
  - Returns: `Page<TramiteDTO>`

- GET `/api/tramite/{id}`  
  - Returns: `TramiteDTO`

- GET `/api/tramite/funcionario/{id}`  
  - Query: `page`, `size`  
  - Requires role: `FUNCIONARIO` or `ADMINISTRATIVO`
  - Returns: `Page<TramiteDTO>` (tramites assigned to funcionario)

- POST `/api/tramite`  
  - Request: `TramiteCreateRequest`  
  - Response: 201 Created (Location header)

- PUT `/api/tramite/{id}`  
  - Request: `TramiteDTO`  
  - Response: 204 No Content

- PUT `/api/tramite/{id}/estado/{estadoId}`  
  - Requires role: `FUNCIONARIO` or `ADMINISTRATIVO`  
  - Response: 204 No Content

- DELETE `/api/tramite/{id}`  
  - Response: 204 No Content

- POST `/api/tramite/{id}/asignar`  
  - Controller: `TramiteAsignacionController`  
  - Body: `{ "usuarioId": <id> }`  
  - Requires role: `FUNCIONARIO` or `ADMINISTRATIVO`  
  - Response: 204 No Content

- POST `/api/tramite/{id}/comentarios`  
  - Body: `ComentarioCreateRequest`  
  - Response: 201 Created with `ComentarioDTO`

- GET `/api/tramite/{id}/comentarios`  
  - Returns: `List<ComentarioDTO>` (timeline)

- GET `/api/tramite/{id}/seguimiento`  
  - Returns: `List<SeguimientoDTO>` (seguimiento timeline)

## TipoTramite (`/api/tipoTramite`)

- GET `/api/tipoTramite`  
  - Query: `page`, `size`  
  - Returns: `Page<TipoTramiteDTO>`

- GET `/api/tipoTramite/{id}`  
  - Returns: `TipoTramiteDTO`

- POST `/api/tipoTramite`  
  - Request: `TipoTramiteDTO`  
  - Response: 201 Created

- PUT `/api/tipoTramite/{id}`  
  - Request: `TipoTramiteDTO`  
  - Response: 204 No Content

- PUT `/api/tipoTramite/{id}/estado/{estadoId}`  
  - Response: 204 No Content

- DELETE `/api/tipoTramite/{id}`  
  - Response: 204 No Content

- GET `/api/tipoTramite/{id}/documentos-requeridos`  
  - Returns: `List<TipoDocumentoDTO>`

## DocumentoTipoTramite / TipoDocumento

- (Exposed via `DocumentoTipoTramiteController`)
- GET `/api/tipoTramite/{id}/documentos-requeridos`  
  - Returns: `List<TipoDocumentoDTO>`

## ArchivoAdjunto (`/api/archivoAdjunto`)

- GET `/api/archivoAdjunto`  
  - Query: `page`, `size`  
  - Returns: `Page<ArchivoAdjuntoDTO>`

- GET `/api/archivoAdjunto/{id}`  
  - Returns: `ArchivoAdjuntoDTO`

- POST `/api/archivoAdjunto`  
  - Request: `ArchivoAdjuntoDTO`  
  - Response: 201 Created

- PUT `/api/archivoAdjunto/{id}`  
  - Request: `ArchivoAdjuntoDTO`  
  - Response: 204 No Content

- PUT `/api/archivoAdjunto/{id}/estado/{estadoId}`  
  - Response: 204 No Content

- DELETE `/api/archivoAdjunto/{id}`  
  - Response: 204 No Content

## Estado (`/api/estado`)

- GET `/api/estado`  
  - Query: `page`, `size`  
  - Returns: `Page<EstadoDTO>`

- GET `/api/estado/{id}`  
  - Returns: `EstadoDTO`

- POST `/api/estado`  
  - Request: `EstadoDTO`  
  - Response: 201 Created

- PUT `/api/estado/{id}`  
  - Request: `EstadoDTO`  
  - Response: 204 No Content

- DELETE `/api/estado/{id}`  
  - Response: 204 No Content

## Rol (`/api/rol`)

- GET `/api/rol`  
  - Query: `page`, `size`  
  - Returns: `Page<RolDTO>`

- GET `/api/rol/{id}`  
  - Returns: `RolDTO`

- POST `/api/rol`  
  - Request: `RolDTO`  
  - Requires role: `ADMINISTRATIVO`  
  - Response: 201 Created

- PUT `/api/rol/{id}`  
  - Request: `RolDTO`  
  - Requires role: `ADMINISTRATIVO`  
  - Response: 204 No Content

- PUT `/api/rol/{id}/estado/{estadoId}`  
  - Response: 204 No Content

- DELETE `/api/rol/{id}`  
  - Requires role: `ADMINISTRATIVO`  
  - Response: 204 No Content

## UsuarioRol (`/api/usuarioRol`)

- GET `/api/usuarioRol`  
  - Query: `page`, `size`  
  - Returns: `Page<UsuarioRolDTO>`

- GET `/api/usuarioRol/{id}`  
  - Returns: `UsuarioRolDTO`

- POST `/api/usuarioRol`  
  - Request: `UsuarioRolDTO`  
  - Requires role: `ADMINISTRATIVO`  
  - Response: 201 Created

- PUT `/api/usuarioRol/{id}`  
  - Request: `UsuarioRolDTO`  
  - Requires role: `ADMINISTRATIVO`  
  - Response: 204 No Content

- PUT `/api/usuarioRol/{id}/estado/{estadoId}`  
  - Requires role: `ADMINISTRATIVO`  
  - Response: 204 No Content

- DELETE `/api/usuarioRol/{id}`  
  - Requires role: `ADMINISTRATIVO`  
  - Response: 204 No Content

## TipoIdentificacion (`/api/tipoIdentificacion`)

- GET `/api/tipoIdentificacion`  
  - Query: `page`, `size`  
  - Returns: `Page<TipoIdentificacionDTO>`

- GET `/api/tipoIdentificacion/{id}`  
  - Returns: `TipoIdentificacionDTO`

- POST `/api/tipoIdentificacion`  
  - Request: `TipoIdentificacionDTO`  
  - Response: 201 Created

- PUT `/api/tipoIdentificacion/{id}`  
  - Request: `TipoIdentificacionDTO`  
  - Response: 204 No Content

- PUT `/api/tipoIdentificacion/{id}/estado/{estadoId}`  
  - Response: 204 No Content

- DELETE `/api/tipoIdentificacion/{id}`  
  - Response: 204 No Content

## Persona (`/api/persona`)

- GET `/api/persona`  
  - Query: `page`, `size`  
  - Returns: `Page<PersonaDTO>`

- GET `/api/persona/{id}`  
  - Returns: `PersonaDTO`

- POST `/api/persona`  
  - Request: `PersonaDTO`  
  - Response: 201 Created (returns body)

- PUT `/api/persona/{id}`  
  - Request: `PersonaDTO`  
  - Response: 204 No Content

- PUT `/api/persona/{id}/estado/{estadoId}`  
  - Response: 204 No Content

- DELETE `/api/persona/{id}`  
  - Response: 204 No Content
