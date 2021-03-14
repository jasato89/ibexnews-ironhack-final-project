import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainPageComponent} from "./components/Main/main-page/main-page.component";
import {UserAreaComponent} from "./components/user-area/user-area.component";
import {LoginComponent} from "./components/Auth/login/login.component";
import {CreateUserComponent} from "./components/Auth/create-user/create-user.component";

const routes: Routes = [
  {
    path: '',
    component: MainPageComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
   path: 'create-user',
   component: CreateUserComponent
  },
  {
    path: 'my-profile',
    component: UserAreaComponent
  },
  {
    path:'**',
    component: MainPageComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
