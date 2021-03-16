import {Component, OnInit} from '@angular/core';
import {User} from "../../classes/user/user";
import {AuthService} from "../../services/security-service/auth.service";
import {DomSanitizer} from "@angular/platform-browser";
import {IbexCompany} from "../../classes/ibex-company/ibex-company";
import {NewsService} from "../../services/news-service/news.service";
import * as moment from "moment";
import {Quote} from "../../classes/daily-stocks/quote";
import {DailyStockValuesService} from "../../services/daily-stock-values/daily-stock-values.service";

@Component({
  selector: 'app-user-area',
  templateUrl: './user-area.component.html',
  styleUrls: ['./user-area.component.css']
})
export class UserAreaComponent implements OnInit {

  constructor(private authService: AuthService, private newsService: NewsService, private stockService: DailyStockValuesService) {
  }

  ngOnInit(): void {
    this.refreshUser();
    this.getCompanies();
  }

  user!: User;
  companies: IbexCompany[] = [];
  keyword: string = 'productionName';
  userIbexCompanies: IbexCompany[] = [];
  newsCount: number[] = [];
  showNews: boolean[] = [];
  quote: Quote[] = [];

  getQuotes() {
    this.userIbexCompanies.forEach(ibexCompany => {
      this.stockService.getQuote(ibexCompany.symbol).subscribe(result => {
        if (result != undefined) {
          this.quote.push(result);

        }
      })

    })

  }

  selectEvent(company: IbexCompany) {
    this.authService.addCompany(company).subscribe(result => {
      this.refreshUser();
      this.userIbexCompanies.push(result);
    });
    ;
  }

  refreshUser() {
    this.authService.getMyUser().subscribe(result => {
      this.user = result;
      this.user.companies.forEach(company => {
        this.newsService.getCompanies().subscribe(result => {
          for (let ibexCompany of result) {
            if (ibexCompany.queryName == company.name) {
              company.ibexCompany = ibexCompany;
              this.userIbexCompanies = [];
              this.newsCount = [];
              this.quote = [];
              this.newsService.getAllNewsByCompany(ibexCompany.queryName, 0, 10).subscribe(x => {
                company.ibexCompany.news = x;
                this.userIbexCompanies.push(ibexCompany);
                this.newsCount.push(0);
                this.showNews.push(false);
                this.getQuotes();
              })
            }
          }
        })
      })

    });
  }

  onChangeSearch($event: any) {

  }

  onFocused($event: void) {

  }

  getCompanies() {
    this.newsService.getCompanies().subscribe(result => {
      this.companies = result;
      this.getQuotes();

    })
  }

  deleteCompany(i: number) {
    this.authService.removeCompany(this.user.companies[i].ibexCompany).subscribe(result => {
      this.userIbexCompanies.splice(i, 1);
      this.refreshUser();
    });

  }

  private getNews(ibexCompany: IbexCompany, limit: number) {
    this.newsService.getAllNewsByCompany(ibexCompany.queryName, limit, limit + 10).subscribe(result => {
      ibexCompany.news = result;
    });

  }

  next(i: number) {
    if (this.userIbexCompanies[i].news.length > 0) {
      this.newsCount[i] = this.newsCount[i] + 10;
      this.getNews(this.userIbexCompanies[i], this.newsCount[i]);
    }


  }

  previous(i: number) {

    if (this.newsCount[i] - 10 < 10) {
      this.newsCount[i] = 0;

    } else {
      this.newsCount[i] = this.newsCount[i] - 10;
    }

    this.getNews(this.userIbexCompanies[i], this.newsCount[i]);

  }

  newsVisibility(i: number) {
    this.showNews[i] = !this.showNews[i];
  }

  formatDate(date: string): string {
    return (moment(date)).format('DD-MMM-YYYY HH:mm:ss');
  }

  formatDateOnlyDate(date: string) {
    return (moment(date)).format('DD-MMM-YYYY');
  }

}
