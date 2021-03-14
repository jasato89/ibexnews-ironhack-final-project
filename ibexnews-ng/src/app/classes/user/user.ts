import {IbexCompany} from "../ibex-company/ibex-company";

export class User {

  constructor(private _email: string,
              private _password: string,
              private _name: string,
              private _dob: string,
              private _job: string,
              private _companies: Company[]) {
  }

  private _ibexCompanies! : IbexCompany[];

  get ibexCompanies(): IbexCompany[] {
    return this._ibexCompanies;
  }

  set ibexCompanies(value: IbexCompany[]) {
    this._ibexCompanies = value;
  }

  get companies(): Company[] {
    return this._companies;
  }

  set companies(value: Company[]) {
    this._companies = value;
  }

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  get password(): string {
    return this._password;
  }

  set password(value: string) {
    this._password = value;
  }


  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get dob(): string {
    return this._dob;
  }

  set dob(value: string) {
    this._dob = value;
  }

  get job(): string {
    return this._job;
  }

  set job(value: string) {
    this._job = value;
  }
}

export class Company {
  constructor(private _id: number, private _name: string) {
  }

  private _ibexCompany!: IbexCompany;


  get ibexCompany(): IbexCompany {
    return this._ibexCompany;
  }

  set ibexCompany(value: IbexCompany) {
    this._ibexCompany = value;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }
}
