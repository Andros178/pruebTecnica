import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { TramiteService, TipoTramiteDTO, TipoDocumentoDTO, TramiteCreateRequest } from './services/tramite.service';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-tramites',
  standalone: true,
  imports: [CommonModule, RouterModule, MatCardModule, MatButtonModule, MatSelectModule, MatFormFieldModule, MatInputModule, FormsModule],
  template: `
    <mat-card>
      <h2>Crear Trámite</h2>

      <mat-form-field appearance="fill" style="width:100%">
        <mat-label>Tipo de trámite</mat-label>
        <mat-select [(value)]="selectedTipoId" (selectionChange)="onTipoChange($event.value)">
          <mat-option *ngFor="let t of tipoOptions" [value]="t.id">{{ t.nombre }}</mat-option>
        </mat-select>
      </mat-form-field>

      <div *ngIf="selectedTipo">
        <h3>Descripción</h3>
        <p>{{ selectedTipo.descripcion }}</p>

        <h4>Documentos requeridos</h4>
        <div *ngIf="requiredDocs?.length; else noDocs">
          <div *ngFor="let d of requiredDocs; let i = index" style="margin-bottom:0.5rem">
            <div style="display:flex;align-items:center;gap:1rem">
              <div style="flex:1">
                <strong>{{ d.nombre }}</strong>
                <div style="font-size:0.9rem;color:rgba(0,0,0,0.6)">{{ d.descripcion || '' }}</div>
              </div>
              <div>
                <input type="file" (change)="onFileSelected($event, i)" />
              </div>
            </div>
          </div>
        </div>
        <ng-template #noDocs>
          <p>No hay documentos requeridos para este tipo de trámite.</p>
        </ng-template>

        <h4>Descripción adicional</h4>
        <mat-form-field appearance="fill" style="width:100%">
          <mat-label>Descripción</mat-label>
          <input matInput [(ngModel)]="descripcion" />
        </mat-form-field>

        <div style="margin-top:1rem">
          <button mat-raised-button color="primary" (click)="submit()">Radicar trámite</button>
        </div>
      </div>
    </mat-card>
  `
})
export class TramitesComponent implements OnInit {
  tipoOptions: TipoTramiteDTO[] = [];
  selectedTipoId: number | null = null;
  selectedTipo: TipoTramiteDTO | null = null;
  requiredDocs: TipoDocumentoDTO[] = [];
  // files map: index -> File
  files: Record<number, File | null> = {};
  descripcion = '';

  constructor(private tramiteSvc: TramiteService, private auth: AuthService) {}

  ngOnInit(): void {
    // fetch tipoTramites (page response may be returned)
    this.tramiteSvc.getTipoTramites().subscribe({
      next: (res: any) => {
        // backend may return Page object or array
        this.tipoOptions = res?.content || res || [];
        if (this.tipoOptions.length) {
          this.selectedTipoId = this.tipoOptions[0].id;
          this.onTipoChange(this.selectedTipoId);
        }
      },
      error: (err) => console.error('Error loading tipoTramites', err)
    });
  }

  onTipoChange(id: number) {
    if (!id) return;
    this.tramiteSvc.getTipoTramiteById(id).subscribe({
      next: (t) => this.selectedTipo = t,
      error: (e) => console.error(e)
    });

    this.tramiteSvc.getDocumentosRequeridos(id).subscribe({
      next: (list) => {
        this.requiredDocs = list || [];
        // reset file map
        this.files = {};
      },
      error: (e) => {
        console.error('Error loading required docs', e);
        this.requiredDocs = [];
      }
    });
  }

  onFileSelected(event: Event, index: number) {
    const input = event.target as HTMLInputElement;
    if (!input.files || input.files.length === 0) {
      this.files[index] = null;
      return;
    }
    this.files[index] = input.files[0];
  }

  submit() {
    if (!this.selectedTipoId) {
      alert('Seleccione un tipo de trámite');
      return;
    }

    // build archivos metadata from selected files
    const archivos = Object.keys(this.files).map((k) => {
      const f = this.files[+k];
      if (!f) return null;
      return {
        url: f.name, // backend doesn't use url for storage in current impl; keep filename
        mime: f.type || 'application/octet-stream',
        nombreArchivo: f.name,
        tamano: String(f.size),
      } as any;
    }).filter(Boolean);

    const req: TramiteCreateRequest = {
      tipoTramiteId: this.selectedTipoId,
      descripcion: this.descripcion,
      archivos: archivos as any
    };

    const token = this.auth.getToken() ?? undefined;
    this.tramiteSvc.createTramite(req, token).subscribe({
      next: (resp) => {
        alert('Trámite radicado correctamente');
      },
      error: (err) => {
        console.error(err);
        alert('Error radicando trámite: ' + (err?.message || err));
      }
    });
  }
}
