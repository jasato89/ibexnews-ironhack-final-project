import {Component, Input, OnInit} from '@angular/core';
import {IbexCompany} from "../../../classes/ibex-company/ibex-company";
import {NewsService} from "../../../services/news-service/news.service";
import {Observable} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {map} from "rxjs/operators";
import * as moment from "moment";
import {Quote} from "../../../classes/daily-stocks/quote";
import {DailyStockValuesService} from "../../../services/daily-stock-values/daily-stock-values.service";

@Component({
  selector: 'app-company-main',
  templateUrl: './company-card.component.html',
  styleUrls: ['./company-card.component.css']
})
export class CompanyCardComponent implements OnInit {

  constructor(private newsService: NewsService, public activatedRoute: ActivatedRoute, public stockService: DailyStockValuesService) {
  }


  @Input() ibexCompany: IbexCompany = new IbexCompany("","", "","","", "" );
  offset: number = 0;
  limit: number = 10;
  quote!:Quote;

  ngOnInit(): void {
    this.getQuote();

  }

  getQuote() {
    this.stockService.getQuote(this.ibexCompany.symbol).subscribe(result =>{
      this.quote = result;
    })

  }

  setIbexCompany(company: IbexCompany) {
    this.ibexCompany = company;
    this.offset = 0;
    this.limit = 10;
    this.getNewsByCompany();
  }

  getNewsByCompany() {

    this.newsService.getNewsByCompany(this.ibexCompany.queryName).subscribe(result => {
      this.ibexCompany.news = result;

    })

  }

  previous() {
    if (!(this.offset < 10)) {
      this.offset = this.offset - 10;
      this.getAllNews();
    }
  }

  next() {
    if (this.ibexCompany.news.length > 0) {
      this.offset = this.offset + 10;
      this.getAllNews();
    }
  }

  private getAllNews() {

    this.newsService.getAllNewsByCompany(this.ibexCompany.queryName, this.offset, this.limit).subscribe(result => {
      this.ibexCompany.news = result;

    })

  }

  formatDate(date: string):string {
    return (moment(date)).format('DD-MMM-YYYY HH:mm:ss');
  }

  formatDateOnlyDate(date :string) {
    return (moment(date)).format('DD-MMM-YYYY');
  }

}
