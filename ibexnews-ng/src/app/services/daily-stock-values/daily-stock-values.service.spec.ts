import { TestBed } from '@angular/core/testing';

import { DailyStockValuesService } from './daily-stock-values.service';

describe('DailyStockValuesService', () => {
  let service: DailyStockValuesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DailyStockValuesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
