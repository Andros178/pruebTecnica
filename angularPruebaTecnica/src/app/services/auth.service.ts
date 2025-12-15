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
}
