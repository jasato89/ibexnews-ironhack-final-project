import {Component, Input, OnInit} from '@angular/core';
import {IbexCompany} from "../../../classes/ibex-company/ibex-company";
import {NewsService} from "../../../services/news-service/news.service";
import {Observable} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {map} from "rxjs/operators";
import * as moment from "moment";

@Component({
  selector: 'app-company-main',
  templateUrl: './company-card.component.html',
  styleUrls: ['./company-card.component.css']
})
export class CompanyCardComponent implements OnInit {

  constructor(private newsService: NewsService, public activatedRoute: ActivatedRoute) {
  }


  @Input() ibexCompany: IbexCompany = new IbexCompany("","", "","","", "" );
  offset: number = 0;
  limit: number = 10;

  ngOnInit(): void {

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

}
