import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, map, catchError, throwError } from 'rxjs';

export interface TipoTramiteDTO {
  id: number;
  nombre: string;
  descripcion?: string;
}

export interface TipoDocumentoDTO {
  id: number;
  nombre: string;
  descripcion?: string;
}

export interface ArchivoAdjuntoCreateDTO {
  url?: string;
  mime?: string;
  nombreArchivo?: string;
  tamano?: string;
}

export interface TramiteCreateRequest {
  tipoTramiteId: number;
  descripcion?: string;
  archivos?: ArchivoAdjuntoCreateDTO[];
}

export interface TramiteDTO {
  id: number;
  descripcion?: string;
  creadorNombre?: string;
  asignadoNombre?: string;
  estadoId?: number;
  estadoNombre?: string;
  tipoTramiteId?: number;
}

export interface ComentarioCreateRequest {
  mensaje: string;
  estadoId?: number;
}

export interface UsuarioDTO {
  id: number;
  nombre: string;
  personaNombre?: string;
}

@Injectable({ providedIn: 'root' })
export class TramiteService {
  private base = '/api';

  constructor(private http: HttpClient) {}

  getTipoTramites(): Observable<TipoTramiteDTO[]> {
    const url = `${this.base}/tipoTramite?page=0&size=200`;
    return this.http.get<any>(url, { observe: 'response' as const }).pipe(
      map((resp) => {
        const body = resp.body as any;
        if (body == null) return [];
        return body.content || body || [];
      }),
      catchError((err) => {
        const isParseError = err && typeof err.message === 'string' && err.message.includes('Http failure during parsing');
        if (isParseError) {
          const fallback = 'http://localhost:8085/api/tipoTramite?page=0&size=200';
          return this.http.get<any>(fallback, { observe: 'response' as const }).pipe(
            map((resp) => {
              const body = resp.body as any;
              if (body == null) return [];
              return body.content || body || [];
            }),
            catchError((e) => throwError(() => e))
          );
        }
        return throwError(() => err);
      })
    ) as unknown as Observable<TipoTramiteDTO[]>;
  }

  getTipoTramiteById(id: number) {
    const url = `${this.base}/tipoTramite/${id}`;
    return this.http.get<TipoTramiteDTO>(url).pipe(
      catchError((err) => {
        const isParseError = err && typeof err.message === 'string' && err.message.includes('Http failure during parsing');
        if (isParseError) {
          const fallback = `http://localhost:8085/api/tipoTramite/${id}`;
          return this.http.get<TipoTramiteDTO>(fallback);
        }
        return throwError(() => err);
      })
    );
  }

  getDocumentosRequeridos(tipoTramiteId: number): Observable<TipoDocumentoDTO[]> {
    const url = `${this.base}/tipoTramite/${tipoTramiteId}/documentos-requeridos`;
    return this.http.get<TipoDocumentoDTO[]>(url).pipe(
      catchError((err) => {
        const isParseError = err && typeof err.message === 'string' && err.message.includes('Http failure during parsing');
        if (isParseError) {
          const fallback = `http://localhost:8085/api/tipoTramite/${tipoTramiteId}/documentos-requeridos`;
          return this.http.get<TipoDocumentoDTO[]>(fallback);
        }
        return throwError(() => err);
      })
    );
  }

  createTramite(req: TramiteCreateRequest, token?: string) {
    const headers = token
      ? new HttpHeaders({ Authorization: `Bearer ${token}` })
      : undefined;
    return this.http.post(`${this.base}/tramite`, req, { headers, observe: 'response' as const }).pipe(
      catchError((err) => {
        // If dev server returned index.html (HTML) or 404 from the dev server, retry direct backend
        const isHtml = err && err.error && typeof err.error === 'string' && err.error.includes('<!DOCTYPE html>');
        const shouldFallback = isHtml || err?.status === 0 || err?.status === 404 || (err && typeof err.message === 'string' && err.message.includes('Http failure response'));
        if (shouldFallback) {
          const fallback = 'http://localhost:8085/api/tramite';
          return this.http.post(fallback, req, { headers, observe: 'response' as const }).pipe(
            catchError((e) => throwError(() => e))
          );
        }
        return throwError(() => err);
      })
    );
  }

  // list tramites (page)
  getTramites(page = 0, size = 50) {
    const url = `${this.base}/tramite?page=${page}&size=${size}`;
    return this.http.get<any>(url, { observe: 'response' as const }).pipe(
      map((resp) => {
        const body = resp.body as any;
        if (body == null) return { content: [], totalElements: 0 };
        return body;
      }),
      catchError((err) => throwError(() => err))
    );
  }

  asignar(tramiteId: number, usuarioId: number) {
    const url = `${this.base}/tramite/${tramiteId}/asignar`;
    return this.http.post(url, { usuarioId }, { observe: 'response' as const });
  }

  addComentario(tramiteId: number, req: ComentarioCreateRequest) {
    const url = `${this.base}/tramite/${tramiteId}/comentarios`;
    return this.http.post<ComentarioCreateRequest>(url, req);
  }

  getComentarios(tramiteId: number) {
    const url = `${this.base}/tramite/${tramiteId}/comentarios`;
    return this.http.get<any[]>(url).pipe(catchError((err) => throwError(() => err)));
  }

  updateEstado(tramiteId: number, estadoId: number) {
    const url = `${this.base}/tramite/${tramiteId}/estado/${estadoId}`;
    return this.http.put(url, {} , { observe: 'response' as const });
  }

  getUsuariosByRole(rolNombre: string) {
    const url = `${this.base}/usuario/role/${rolNombre}`;
    return this.http.get<UsuarioDTO[]>(url).pipe(catchError((err) => throwError(() => err)));
  }

  getEstados() {
    const url = `${this.base}/estado?page=0&size=200`;
    return this.http.get<any>(url, { observe: 'response' as const }).pipe(
      map((resp) => {
        const body = resp.body as any;
        if (body == null) return [];
        return body.content || body || [];
      }),
      catchError((err) => throwError(() => err))
    );
  }
}
