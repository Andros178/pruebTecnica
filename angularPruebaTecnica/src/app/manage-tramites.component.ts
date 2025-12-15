import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-manage-tramites',
  standalone: true,
  imports: [CommonModule, MatCardModule],
  template: `
    <mat-card>
      <h2>Gestión de trámites</h2>
      <p>Página de gestión (placeholder).</p>
    </mat-card>
  `
})
export class ManageTramitesComponent {}
