import {News} from "../news/news";
import {List} from "postcss/lib/list";
import {Daily} from "../../services/daily-stock-values/daily-stock-values.service";
import {DailyStocks} from "../daily-stocks/daily-stocks";

export class IbexCompany {

  constructor(private _queryName: string,
              private _symbol: string,
              private _productionName: string,
              private _cincoDiasTagName: string,
              private _elConfidencialTagName: string,
              private _elEconomistaTagName: string) {
  }

  private _news: News[] = [];
  private _daily! : DailyStocks;


  get daily(): DailyStocks {
    return this._daily;
  }

  set daily(value: DailyStocks) {
    this._daily = value;
  }

  get news(): News[] {
    return this._news;
  }

  set news(value: News[]) {
    this._news = value;
  }

  get queryName(): string {
    return this._queryName;
  }

  set queryName(value: string) {
    this._queryName = value;
  }

  get symbol(): string {
    return this._symbol;
  }

  set symbol(value: string) {
    this._symbol = value;
  }

  get productionName(): string {
    return this._productionName;
  }

  set productionName(value: string) {
    this._productionName = value;
  }

  get cincoDiasTagName(): string {
    return this._cincoDiasTagName;
  }

  set cincoDiasTagName(value: string) {
    this._cincoDiasTagName = value;
  }

  get elConfidencialTagName(): string {
    return this._elConfidencialTagName;
  }

  set elConfidencialTagName(value: string) {
    this._elConfidencialTagName = value;
  }

  get elEconomistaTagName(): string {
    return this._elEconomistaTagName;
  }

  set elEconomistaTagName(value: string) {
    this._elEconomistaTagName = value;
  }
}
