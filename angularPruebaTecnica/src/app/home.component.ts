import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule, MatCardModule, MatButtonModule],
  template: `
    <mat-card>
      <h2>Bienvenido</h2>
      <p>Seleccione una acción:</p>

      <div style="display:flex;flex-direction:column;gap:1rem;max-width:400px">
        <button mat-raised-button color="primary" routerLink="/tramites" *ngIf="canRadicar()">Radicar trámites</button>
        <button mat-raised-button color="accent" routerLink="/manage-tramites" *ngIf="canGestionarTramites()">Gestionar trámites</button>
        <button mat-raised-button color="warn" routerLink="/manage-usuarios" *ngIf="canGestionarUsuarios()">Gestionar usuarios</button>
      </div>
    </mat-card>
  `
})
export class HomeComponent {
  constructor(private auth: AuthService) {}

  private roles(): string[] {
    return this.auth.getRoles().map(r => (r || '').toString().toUpperCase());
  }

  canRadicar(): boolean {
    const allowed = ['ESTUDIANTE', 'DOCENTE', 'ADMINISTRATIVO', 'FUNCIONARIO', 'ADMINISTRADOR'];
    return this.roles().some(r => allowed.includes(r));
  }

  canGestionarTramites(): boolean {
    const allowed = ['ADMINISTRATIVO', 'FUNCIONARIO', 'ADMINISTRADOR'];
    return this.roles().some(r => allowed.includes(r));
  }

  canGestionarUsuarios(): boolean {
    const allowed = ['ADMINISTRADOR', 'ADMINISTRATIVO'];
    return this.roles().some(r => allowed.includes(r));
  }
}
