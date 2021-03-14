import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {IbexCompany} from "../../../classes/ibex-company/ibex-company";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  @Input() ibexCompany!: IbexCompany;
  @Input() isSearching: boolean = false;

  @Output() currentIbexCompany: EventEmitter<IbexCompany> = new EventEmitter();

  setCurrentIbexCompany(company: IbexCompany) {
    console.log(company.productionName);
    this.ibexCompany = company;

  }

  userIsSearching(bool: boolean) {
    this.isSearching = bool;
  }

  setIbexCompany() {

  }
}
