import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {NewsService} from "../../../services/news-service/news.service";
import {News} from "../../../classes/news/news";
import {IbexCompany} from "../../../classes/ibex-company/ibex-company";
import {DailyStockValuesService} from "../../../services/daily-stock-values/daily-stock-values.service";
import * as moment from 'moment';

@Component({
  selector: 'app-main-news',
  templateUrl: './main-news.component.html',
  styleUrls: ['./main-news.component.css']
})
export class MainNewsComponent implements OnInit {

  constructor(private newsService: NewsService, private dailyStocks: DailyStockValuesService) {
  }



  ngOnInit(): void {
    this.getNews();
    this.getCompanies();


  }

  offset: number = 0;
  limit: number = 10;
  articles: News[] = [];
  companies: IbexCompany[] = [];
  ibexCompanyNews: News[] = [];
  isActive: boolean[] = [];
  mainIsActive: boolean = true;




  getNews() {
    this.newsService.getNews(this.offset, this.limit).subscribe(result => {
      this.articles = result;
    })
  }

  getCompanies() {
    this.newsService.getCompanies().subscribe(result => {
      this.companies = result;
    })

  }

  formatDate(date: string):string {
    return (moment(date)).format('DD-MMM-YYYY HH:mm:ss');
  }

  getNewsByCompany() {
    this.companies.forEach(company=> {
      this.newsService.getNewsByCompany(company.queryName).subscribe(result=>{
        if (result != undefined) {
        company.news = result;

        }
      })
    })

  }


  previous() {
    if (!(this.offset < 10)) {
      this.offset = this.offset - 10;
      this.getNews();
    }
  }

  next() {
    if (this.articles.length > 0) {
      this.offset = this.offset + 10;
      this.getNews();
    }
  }

  selectCompany(i: number) {
    this.newsService.getNewsByCompany(this.companies[i].queryName).subscribe(result=>{
      if (result != undefined) {
        this.companies[i].news = result;

      }
    })
  }

}
