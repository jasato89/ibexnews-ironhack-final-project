import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './components/menu/menu.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { MainNewsComponent } from './components/Main/main-news/main-news.component';
import { CreateUserComponent } from './components/Auth/create-user/create-user.component';
import { UserAreaComponent } from './components/user-area/user-area.component';
import { LoginComponent } from './components/Auth/login/login.component';
import { CompanyCardComponent } from './components/Main/company-card/company-card.component';
import { IbexSearchComponent } from './components/Main/ibex-search/ibex-search.component';
import {AutocompleteLibModule} from "angular-ng-autocomplete";
import { MainPageComponent } from './components/Main/main-page/main-page.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    MainNewsComponent,
    CreateUserComponent,
    UserAreaComponent,
    LoginComponent,
    CompanyCardComponent,
    IbexSearchComponent,
    MainPageComponent,
    ],
  imports: [
    HttpClientModule,
    BrowserAnimationsModule,
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    AutocompleteLibModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
