import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from './services/auth.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatToolbarModule } from '@angular/material/toolbar';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule, RouterModule,
    MatFormFieldModule, MatInputModule, MatButtonModule, MatCardModule, MatProgressSpinnerModule],
  templateUrl: './login.component.html'
})
export class LoginComponent {
  nombre = '';
  contrasena = '';

  loading = false;

  constructor(private auth: AuthService, private router: Router) {}

  submit() {
    this.loading = true;
    this.auth
      .login({ nombre: this.nombre, contrasena: this.contrasena })
      .subscribe({
        next: (res) => {
          this.auth.saveToken(res.token);
          alert('Login exitoso — token guardado.');
          this.loading = false;
        },
        error: (err) => {
          alert('Error en login: ' + (err?.error || err?.message || err));
          this.loading = false;
        }
      });
  }
}
