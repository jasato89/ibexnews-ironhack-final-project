import {IbexCompany} from "../ibex-company/ibex-company";

export class News {
  constructor(private _id: number,
              private _localDateTime: string,
              private _title: string,
              private _url: string,
              private _mediaOutlet: string,
              private _ibexCompany: string
              ) {}


  get url(): string {
    return this._url;
  }

  set url(value: string) {
    this._url = value;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get localDateTime(): string {
    return this._localDateTime;
  }

  set localDateTime(value: string) {
    this._localDateTime = value;
  }

  get title(): string {
    return this._title;
  }

  set title(value: string) {
    this._title = value;
  }

  get mediaOutlet(): string {
    return this._mediaOutlet;
  }

  set mediaOutlet(value: string) {
    this._mediaOutlet = value;
  }

  get ibexCompany(): string {
    return this._ibexCompany;
  }

  set ibexCompany(value: string) {
    this._ibexCompany = value;
  }
}
