import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {
  fadeInDownOnEnterAnimation,
  fadeInOnEnterAnimation,
  fadeInUpOnEnterAnimation,
  fadeOutOnLeaveAnimation,
  fadeOutUpOnLeaveAnimation
} from 'angular-animations';
import {AuthService} from "../../services/security-service/auth.service";



@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
  animations: [
    fadeInOnEnterAnimation(),
    fadeOutOnLeaveAnimation(),
    fadeInDownOnEnterAnimation(),
    fadeOutUpOnLeaveAnimation()

  ]
})
export class MenuComponent implements OnInit {

  constructor(private authService: AuthService) { }

  menu:boolean = false;
  @Output() employees: EventEmitter<void> = new EventEmitter();
  isLogged : boolean = this.authService.isLogged;


  ngOnInit(): void {
    this.authService.restoreSession();
  }

  showMenu() {
    this.menu = !this.menu;
    this.isLogged = this.authService.isLogged;
    console.log(this.authService.getJwt());
  }


  logout() {
    this.authService.logout();
    this.isLogged = this.authService.isLogged;
  }

  hideMenu() {
    this.menu = false;
  }
}
