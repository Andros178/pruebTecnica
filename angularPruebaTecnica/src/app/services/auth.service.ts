import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface LoginRequest {
  nombre: string;
  contrasena: string;
}

interface RegisterRequest {
  nombre: string;
  contrasena: string;
  personaId: number;
}

export interface LoginResponse {
  token: string;
  nombre: string;
  id: number;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private base = 'http://localhost:8085/api';

  constructor(private http: HttpClient) {}

  login(req: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.base}/auth/login`, req);
  }

  register(req: RegisterRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.base}/auth/register`, req);
  }

  saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  logout(): void {
    localStorage.removeItem('token');
  }

  private decodePayload(token: string): any | null {
    try {
      const parts = token.split('.');
      if (parts.length < 2) return null;
      const payload = parts[1];
      // base64url -> base64
      const b64 = payload.replace(/-/g, '+').replace(/_/g, '/');
      const pad = b64.length % 4 === 0 ? '' : '='.repeat(4 - (b64.length % 4));
      const json = atob(b64 + pad);
      return JSON.parse(json);
    } catch (e) {
      return null;
    }
  }

  getUserName(): string | null {
    const token = this.getToken();
    if (!token) return null;
    const p = this.decodePayload(token);
    if (!p) return null;
    return (p.nombre || p.name || p.username || p.sub) ?? null;
  }

  getRoles(): string[] {
    const token = this.getToken();
    if (!token) return [];
    const p = this.decodePayload(token);
    if (!p) return [];
    // Try common claim names
    const roles = p.roles || p.authorities || p.role || p.rol;
    if (!roles) return [];
    if (Array.isArray(roles)) return roles;
    if (typeof roles === 'string') return roles.split(',').map((r: string) => r.trim());
    return [];
  }

  isTokenExpired(): boolean {
    const token = this.getToken();
    if (!token) return true;
    const p = this.decodePayload(token);
    if (!p) return true;
    const exp = p.exp;
    if (!exp) return false; // no exp claim -> assume not expired
    const now = Math.floor(Date.now() / 1000);
    return now >= exp;
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    if (!token) return false;
    if (this.isTokenExpired()) return false;
    return true;
  }
}
