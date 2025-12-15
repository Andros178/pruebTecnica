import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { TramiteService, TramiteDTO, UsuarioDTO } from './services/tramite.service';

@Component({
  selector: 'app-manage-tramites',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatButtonModule, MatSelectModule, MatFormFieldModule, MatInputModule, FormsModule],
  template: `
    <mat-card>
      <h2>Gestión de trámites</h2>

      <div *ngIf="tramites?.length; else noTramites">
        <div *ngFor="let t of tramites" style="border:1px solid #eee;padding:0.5rem;margin-bottom:0.5rem">
          <div style="display:flex;justify-content:space-between;align-items:center">
            <div>
              <strong>#{{t.id}}</strong> — {{ t.descripcion }}
              <div style="font-size:0.9rem;color:rgba(0,0,0,0.6)">Creador: {{ t.creadorNombre || 'N/A' }} — Asignado: {{ t.asignadoNombre || 'Sin asignar' }}</div>
              <div style="font-size:0.9rem;color:rgba(0,0,0,0.6)">Estado: {{ t.estadoNombre || 'N/A' }}</div>
            </div>
            <div style="display:flex;gap:0.5rem">
              <button mat-button (click)="openAssign(t)">Asignar</button>
              <button mat-button (click)="openComment(t)">Comentar</button>
              <button mat-button (click)="openChangeEstado(t)">Cambiar estado</button>
            </div>
          </div>

          <div *ngIf="activeAssignFor===t.id" style="margin-top:0.5rem">
            <mat-form-field appearance="fill">
              <mat-label>Funcionario</mat-label>
              <mat-select [(value)]="selectedFuncionarioId">
                <mat-option *ngFor="let u of funcionarios" [value]="u.id">{{ u.nombre }} ({{ u.personaNombre || '' }})</mat-option>
              </mat-select>
            </mat-form-field>
            <button mat-raised-button color="primary" (click)="assign(t)">Confirmar asignación</button>
            <button mat-button (click)="cancelAssign()">Cancelar</button>
          </div>

          <div *ngIf="activeCommentFor===t.id" style="margin-top:0.5rem">
            <mat-form-field appearance="fill" style="width:60%">
              <mat-label>Comentario</mat-label>
              <input matInput [(ngModel)]="commentText" />
            </mat-form-field>
            <button mat-raised-button color="primary" (click)="addComment(t)">Agregar comentario</button>
            <button mat-button (click)="cancelComment()">Cancelar</button>
          </div>

          <div *ngIf="activeEstadoFor===t.id" style="margin-top:0.5rem">
            <mat-form-field appearance="fill">
              <mat-label>Estado</mat-label>
              <mat-select [(value)]="selectedEstadoId">
                <mat-option *ngFor="let e of estados" [value]="e.id">{{ e.nombre }}</mat-option>
              </mat-select>
            </mat-form-field>
            <button mat-raised-button color="primary" (click)="changeEstado(t)">Cambiar estado</button>
            <button mat-button (click)="cancelEstado()">Cancelar</button>
          </div>

        </div>
      </div>

      <ng-template #noTramites>
        <p>No hay trámites.</p>
      </ng-template>
    </mat-card>
  `
})
export class ManageTramitesComponent implements OnInit {
  tramites: TramiteDTO[] = [];
  funcionarios: UsuarioDTO[] = [];
  estados: any[] = [];

  // UI state
  activeAssignFor: number | null = null;
  selectedFuncionarioId: number | null = null;

  activeCommentFor: number | null = null;
  commentText = '';

  activeEstadoFor: number | null = null;
  selectedEstadoId: number | null = null;

  constructor(private tramiteSvc: TramiteService) {}

  ngOnInit(): void {
    this.loadTramites();
    this.loadEstados();
  }

  loadTramites() {
    this.tramiteSvc.getTramites(0,50).subscribe({
      next: (resp: any) => {
        this.tramites = resp.content || [];
      },
      error: (e) => console.error('Error loading tramites', e)
    });
  }

  loadEstados() {
    this.tramiteSvc.getEstados().subscribe({
      next: (list: any[]) => this.estados = list,
      error: (e) => console.error('Error loading estados', e)
    });
  }

  openAssign(t: TramiteDTO) {
    this.activeAssignFor = t.id;
    this.selectedFuncionarioId = null;
    // load funcionarios
    this.tramiteSvc.getUsuariosByRole('FUNCIONARIO').subscribe({
      next: (list) => this.funcionarios = list || [],
      error: (e) => console.error('Error loading funcionarios', e)
    });
  }

  assign(t: TramiteDTO) {
    if (!this.selectedFuncionarioId) return alert('Seleccione un funcionario');
    this.tramiteSvc.asignar(t.id, this.selectedFuncionarioId).subscribe({
      next: () => {
        alert('Asignación realizada');
        this.cancelAssign();
        this.loadTramites();
      },
      error: (e) => { console.error(e); alert('Error al asignar'); }
    });
  }

  cancelAssign() {
    this.activeAssignFor = null;
    this.selectedFuncionarioId = null;
  }

  openComment(t: TramiteDTO) {
    this.activeCommentFor = t.id;
    this.commentText = '';
  }

  addComment(t: TramiteDTO) {
    if (!this.commentText || this.commentText.trim().length === 0) return alert('Comentario vacío');
    this.tramiteSvc.addComentario(t.id, { mensaje: this.commentText }).subscribe({
      next: () => {
        alert('Comentario agregado');
        this.cancelComment();
      },
      error: (e) => { console.error(e); alert('Error agregando comentario'); }
    });
  }

  cancelComment() {
    this.activeCommentFor = null;
    this.commentText = '';
  }

  openChangeEstado(t: TramiteDTO) {
    this.activeEstadoFor = t.id;
    this.selectedEstadoId = t.estadoId || null;
  }

  changeEstado(t: TramiteDTO) {
    if (!this.selectedEstadoId) return alert('Seleccione estado');
    this.tramiteSvc.updateEstado(t.id, this.selectedEstadoId).subscribe({
      next: () => { alert('Estado actualizado'); this.cancelEstado(); this.loadTramites(); },
      error: (e) => { console.error(e); alert('Error actualizando estado'); }
    });
  }

  cancelEstado() {
    this.activeEstadoFor = null;
    this.selectedEstadoId = null;
  }
}
