import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DailyStockValuesService {

  constructor(private http: HttpClient) { }


  readonly url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=";
  readonly apiKey = "&apikey=DRZBW9X80AQOMLLR";

  getStockValue(stockValue: string): Observable<Daily> {
    return this.http.get<Daily>(this.url + stockValue +this.apiKey);
  }


}

export interface Daily {
  "Meta Data":           MetaDataDaily;
  "Time Series (Daily)": { [key: string]: TimeSeriesDaily };
}

export interface MetaDataDaily {
  "1. Information":    string;
  "2. Symbol":         string;
  "3. Last Refreshed": Date;
  "4. Output Size":    string;
  "5. Time Zone":      string;
}

export interface TimeSeriesDaily {
  "1. open":   string;
  "2. high":   string;
  "3. low":    string;
  "4. close":  string;
  "5. volume": string;
}
