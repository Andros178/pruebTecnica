# pruebTecnica
Este repositorio está destinado para el almacenamiento y seguimiento del desarrollo del proyecto

La colección de POSTMAN también ha sido envíada a los respectivos correos, sin embargo se añade un link de invitación en caso de presentarse inconvenientes en la importación del JSON
https://app.getpostman.com/join-team?invite_code=0162b24bebef24ec87f879005b2be55e3ba816b1f83d909344e9af35bdfeefea&target_code=a0475ecf3e563662cfa25805dcfb01f8
Se utilizó FreeMarker para generar una estructura base apartir de un JSON

Ejecutar mvn spring-boot:run en el directorio generator para generar un directorio "output" del cual se extrajo la estructura inicial del proyecto

## Guía rápida — ejecutar Backend y Frontend

Estas instrucciones suponen que se trabaja en Linux (bash) e instalados Java 21 (o compatible), Git, Node.js (recomendado v18+) y npm. El backend usa PostgreSQL por defecto — ajustar `application.properties`.

### Backend (Spring Boot — módulo `usco`)

- Puerto por defecto: `8085` (ver `usco/src/main/resources/application.properties`).
- Configuración de base de datos por defecto (puedes cambiarla en `application.properties`):
	- URL: `jdbc:postgresql://localhost:5432/pruebaTecnica`
	- Usuario: `postgres`
	- Contraseña: `2417`

Pasos básicos:

1. Ir al directorio del backend:

```bash
cd usco
```

2. Ejecutar con el wrapper Maven (modo desarrollo):

```bash
./mvnw spring-boot:run
```

3. (Opcional) Empaquetar y ejecutar el JAR:

```bash
./mvnw package -DskipTests
java -jar target/usco-0.0.1-SNAPSHOT.jar
```


### Frontend (Angular — carpeta `angularPruebaTecnica`)

- Puerto por defecto del dev-server: `4200`.

Pasos básicos:

1. Ir al directorio del frontend:

```bash
cd angularPruebaTecnica
```

2. Instalar dependencias (primera vez):

```bash
npm install
```

3. Ejecutar el servidor de desarrollo:

```bash
npm start
# o: npx ng serve
```

4. Abrir en el navegador: `http://localhost:4200`


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

