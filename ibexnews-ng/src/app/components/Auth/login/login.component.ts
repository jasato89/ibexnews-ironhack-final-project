import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../services/security-service/auth.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserLogin} from "../../../classes/user-login/user-login";
import {User} from "../../../classes/user/user";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthService, private route: Router) {
    this.emailField = new FormControl('', [Validators.required, Validators.email]);
    this.passwordField = new FormControl('', [Validators.required, Validators.minLength(7)]);

    this.form = new FormGroup({
      email: this.emailField,
      password: this.passwordField
    });
  }


  ngOnInit(): void {
  }

  form: FormGroup;
  emailField: FormControl;
  passwordField: FormControl;

  onSubmit() {
    this.authService.authenticate(new UserLogin(this.emailField.value, this.passwordField.value));

  }

  getUser() {
     this.authService.getUser();

  }
}
