import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  imports: [CommonModule, RouterOutlet, RouterModule, MatToolbarModule, MatButtonModule],
  templateUrl: './app.shell.html',
  styleUrl: './app.less'
})
export class App {
  protected readonly title = signal('angularPruebaTecnica');

  constructor(private auth: AuthService, private router: Router) {}

  isAuthenticated(): boolean {
    return this.auth.isAuthenticated();
  }

  logout(): void {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
  
  getUserName(): string | null {
    return this.auth.getUserName();
  }
}
