import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../../classes/user/user";
import {catchError} from "rxjs/operators";
import {UserLogin} from "../../classes/user-login/user-login";
import {IbexCompany} from "../../classes/ibex-company/ibex-company";
import {NewsService} from "../news-service/news.service";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,private newsService: NewsService, private route: Router) {
  }

  readonly baseUrl: string = "https://ibexnews-feign.herokuapp.com";

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  private jwt!: string | null;
  private _user: User = new User("", "", "", "","", []);
  private _isLogged: boolean = false;



  public createUser(request: User) {
    this.http.post(this.baseUrl + "/create-user", {
      email: request.email,
      password: request.password,
      name: request.name,
      dob: request.dob,
      job: request.job
    }).subscribe(result => {
      window.alert("User created succesfully");
      this.route.navigate(["login"]);



    }, error => {
      window.alert("There was a problem connecting to the database, please try again");
    });

  }

  getJwt(): string {
    return <string>this.jwt;
  }

  public authenticate(request: UserLogin): void {
    this.http.post<Jwt>(this.baseUrl + "/authenticate", {
      email: request.email,
      password: request.password
    },).subscribe(result => {
      this.jwt = result.jwt;
      localStorage.setItem("jwt", <string>result.jwt);
      this._isLogged = true;
      this.getUser();
      this.route.navigate(["my-profile"]);



    }, error=> {
      window.alert("There was a problem connecting to the database, please try again");


    });

  }
  public addCompany(company: IbexCompany):Observable<any> {
    return this.http.get<IbexCompany>(this.baseUrl + "/add-company/" + company.queryName,
      {headers: {'Authorization': 'Bearer ' + this.jwt}})
    ;
  }
  public removeCompany(company: IbexCompany):Observable<any> {
    return this.http.delete(this.baseUrl + "/remove-company/" + company.queryName  , {headers: {'Authorization': 'Bearer ' + this.jwt}});


  }

  public keepLogged() {
    localStorage.setItem("jwt", <string>this.jwt);
  }

  restoreSession() {
    if (localStorage.getItem("jwt") != null) {
      this.jwt = localStorage.getItem("jwt");
      this.isLogged = true;
    }
  }

  public logout() {
    localStorage.removeItem("jwt");
    this.isLogged = false;
  }

  public getUser() {
    this.http.get<User>(this.baseUrl + "/get-user/", {headers: {'Authorization': 'Bearer ' + this.jwt}}).subscribe(result => {
      console.log(result.email);
      this._user = new User(
        result.email,
        result.password,
        result.name,
        result.dob,
        result.job,
        result.companies,
      );

    })

  }

  public getMyUser(): Observable<User> {
    return this.http.get<User>(this.baseUrl + "/get-user/", {headers: {'Authorization': 'Bearer ' + this.jwt}});
  }


  get isLogged(): boolean {
    return this._isLogged;
  }

  set isLogged(value: boolean) {
    this._isLogged = value;
  }

  get user(): User {
    return this._user;
  }

  set user(value: User) {
    this._user = value;
  }
}

export class Jwt {
  constructor(private _jwt: string) {
  }

  get jwt(): string {
    return this._jwt;
  }

  set jwt(value: string) {
    this._jwt = value;
  }
}
