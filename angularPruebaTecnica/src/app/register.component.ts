import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { PersonaService } from './services/persona.service';
import { AuthService } from './services/auth.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule,
    MatFormFieldModule, MatInputModule, MatSelectModule, MatButtonModule, MatCardModule],
  templateUrl: './register.component.html'
})
export class RegisterComponent implements OnInit {
  // persona fields
  nombre = '';
  segundoNombre = '';
  apellido = '';
  segundoApellido = '';
  correo = '';
  numeroIdentificacion = '';
  tipoIdentificacionId: number | null = null;

  // credentials
  usuarioNombre = '';
  contrasena = '';

  tipoOptions: any[] = [];
  loading = false;

  constructor(private personaSvc: PersonaService, private auth: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.personaSvc.getTipoIdentificaciones().subscribe({
      next: (list) => {
        this.tipoOptions = list;
        if (list.length) this.tipoIdentificacionId = list[0].id;
      },
      error: (err) => console.error('Error cargando tipos', err)
    });
  }

  submit() {
    // basic client-side validation to avoid sending empty required fields
    if (!this.nombre || !this.apellido || !this.correo || !this.numeroIdentificacion) {
      alert('Por favor complete los campos requeridos: nombre, apellido, correo y número de identificación');
      return;
    }

    if (!this.tipoIdentificacionId) {
      alert('Seleccione un tipo de identificación');
      return;
    }

    this.loading = true;

    const persona = {
      nombre: this.nombre,
      segundoNombre: this.segundoNombre || undefined,
      apellido: this.apellido,
      segundoApellido: this.segundoApellido || undefined,
      correo: this.correo || undefined,
      numeroIdentificacion: this.numeroIdentificacion,
      tipoIdentificacionId: this.tipoIdentificacionId
    };

    this.personaSvc.createPersona(persona as any).subscribe({
      next: (personaId) => {
        // now register user with personaId
        this.auth.register({ nombre: this.usuarioNombre || this.nombre, contrasena: this.contrasena, personaId })
          .subscribe({
            next: (resp) => {
              this.auth.saveToken(resp.token);
              alert('Registro exitoso. Token guardado.');
              this.loading = false;
              this.router.navigate(['/login']);
            },
            error: (err) => {
              alert('Error registrando usuario: ' + (err?.error || err?.message || err));
              this.loading = false;
            }
          });
      },
      error: (err) => {
        alert('Error creando persona: ' + (err?.error || err?.message || err));
        this.loading = false;
      }
    });
  }
}
