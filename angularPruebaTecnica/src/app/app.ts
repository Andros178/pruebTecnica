import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterModule, MatToolbarModule, MatButtonModule],
  templateUrl: './app.shell.html',
  styleUrl: './app.less'
})
export class App {
  protected readonly title = signal('angularPruebaTecnica');
}
