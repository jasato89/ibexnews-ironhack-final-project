import { Component } from '@angular/core';
import {AuthService} from "./services/security-service/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Ibex News';

  constructor(private authService: AuthService) {
    this.authService.restoreSession();
  }
}
