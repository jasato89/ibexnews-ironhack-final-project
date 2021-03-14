import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {News} from "../../classes/news/news";
import {Observable} from "rxjs";
import {IbexCompany} from "../../classes/ibex-company/ibex-company";

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(private http: HttpClient) {
  }

  readonly baseUrl: string = "https://ibexnews-feign.herokuapp.com";



  getNews(offset: number, limit: number): Observable<News[]> {
    console.log(offset);
    return this.http.get<News[]>(this.baseUrl + "/get-news/all?offset=" + offset +"&limit="+ limit);
  }

  getCompanies() {
    return this.http.get<IbexCompany[]>(this.baseUrl + "/get-companies");
  }

   getNewsByCompany(company: string) : Observable<News[]> {
     return this.http.get<News[]>(this.baseUrl + "/get-news/company/" + company);

  }

  getAllNewsByCompany(company: string, offset: number, limit: number) : Observable<News[]> {
    return this.http.get<News[]>(this.baseUrl + "/get-news/company/" + company + "?offset=" + offset +"&limit="+ limit);

  }

}
