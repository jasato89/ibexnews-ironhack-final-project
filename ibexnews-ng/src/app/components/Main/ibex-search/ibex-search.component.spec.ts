import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IbexSearchComponent } from './ibex-search.component';

describe('IbexSearchComponent', () => {
  let component: IbexSearchComponent;
  let fixture: ComponentFixture<IbexSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IbexSearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IbexSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
