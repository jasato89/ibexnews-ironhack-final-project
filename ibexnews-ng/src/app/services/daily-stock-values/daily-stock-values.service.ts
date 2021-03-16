import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, timer} from "rxjs";
import {Quote} from "../../classes/daily-stocks/quote";
import {delayWhen, map, retryWhen} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class DailyStockValuesService {

  constructor(private http: HttpClient) {
  }


  readonly url = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=";
  readonly apiKey = "&apikey=DRZBW9X80AQOMLLR";

  // Gets a quote for the specified symbol.
  public getQuote(symbol: string): Observable<Quote> {

    let observable: Observable<Quote>;

    observable = this.getQuoteFromRESTService(symbol);

    return observable;

  }

  private getQuoteFromRESTService(symbol: string): Observable<Quote> {

    let url = this.url + symbol + "&apikey=" + this.apiKey;
    console.log(symbol);

    return this.http.get<Quote>(url).pipe(
      map(res => {
        let result: Quote;
        // @ts-ignore
        result = new Quote(
          // @ts-ignore
          res["Global Quote"]["01. symbol"],
          // @ts-ignore
          res["Global Quote"]["02. open"],
          // @ts-ignore
          res["Global Quote"]["03. high"],
          // @ts-ignore
          res["Global Quote"]["04. low"],
          // @ts-ignore
          res["Global Quote"]["05. price"],
          // @ts-ignore
          res["Global Quote"]["06. volume"],
          // @ts-ignore
          res["Global Quote"]["07. latest trading day"],
          // @ts-ignore
          res["Global Quote"]["08. previous close"],
          // @ts-ignore
          res["Global Quote"]["09. change"],
          // @ts-ignore
          res["Global Quote"]["10. change percent"]
        )
        console.log(result.high);

        return result;
      }),
      retryWhen(errors =>
        errors.pipe(
          // Retry the query again in a little bit ...
          delayWhen(val => timer(30000))
        )
      )
    )
  }

}



