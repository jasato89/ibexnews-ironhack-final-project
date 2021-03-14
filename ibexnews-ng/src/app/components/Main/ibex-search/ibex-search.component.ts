import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AutocompleteLibModule} from 'angular-ng-autocomplete';
import {IbexCompany} from "../../../classes/ibex-company/ibex-company";
import {NewsService} from "../../../services/news-service/news.service";
import {ActivatedRoute, Router} from "@angular/router";


@Component({
  selector: 'app-ibex-search',
  templateUrl: './ibex-search.component.html',
  styleUrls: ['./ibex-search.component.css']
})
export class IbexSearchComponent implements OnInit {

  constructor(private newsService: NewsService, private route: Router) {
  }

  ngOnInit(): void {
    this.getCompanies();
  }

  companies: IbexCompany[] = [];
  keyword: string = 'productionName';
  @Output() companySelectedEvent: EventEmitter<IbexCompany> = new EventEmitter();
  @Output() isSearching: EventEmitter<boolean> = new EventEmitter();
  backToNews: boolean = false;


  selectEvent(company: IbexCompany) {
    this.getNewsByCompany();

    this.companySelectedEvent.emit(company);
    this.isSearching.emit(false);
    this.backToNews = true;

  }

  onChangeSearch(searchTerm: string) {
    if (searchTerm == "") {
      this.isSearching.emit(true);
      this.backToNews = false;
    }

  }

  onFocused($event: any) {

  }


  getCompanies() {
    this.newsService.getCompanies().subscribe(result => {
      this.companies = result;
      this.getNewsByCompany();
      this.isSearching.emit(true);

    })

  }

  getNewsByCompany() {

    this.companies.forEach(ibexCompany => {
      this.newsService.getNewsByCompany(ibexCompany.queryName).subscribe(result => {
        ibexCompany.news = result;

      })
    })

  }

}

