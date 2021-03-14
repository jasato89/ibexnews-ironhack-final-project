import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../services/security-service/auth.service";
import {User} from "../../../classes/user/user";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  form: FormGroup;
  nameField: FormControl;
  emailField: FormControl;
  passwordField: FormControl;
  dateOfBirthField: FormControl;
  jobField: FormControl;

  name: string = '';

  constructor(private authService : AuthService) {
    // Initialize Form Control fields
    this.nameField = new FormControl('', [Validators.required]);
    this.emailField = new FormControl('', [Validators.required, Validators.email]);
    this.passwordField = new FormControl('', [Validators.required, Validators.minLength(7)]);
    this.dateOfBirthField = new FormControl('', [Validators.required]);
    this.jobField = new FormControl('', [Validators.required]);


    // Initialzie Form Group
    this.form = new FormGroup({
      name: this.nameField,
      email: this.emailField,
      password: this.passwordField,
      dateOfBirth: this.dateOfBirthField,
      job: this.jobField
    });
  }


  ngOnInit(): void {
  }

  createNewUser() {

  }


  onSubmit() {

    this.authService.createUser(new User(
      this.emailField.value,
      this.passwordField.value,
      this.nameField.value,
      this.dateOfBirthField.value,
      this.jobField.value,
      []
    ))

  }

  readUrl(event: any) {
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();
      reader.onload = (event: any) => {
        this.name = event.target.result;
      }
      reader.readAsDataURL(event.target.files[0]);
    }
  }
}
