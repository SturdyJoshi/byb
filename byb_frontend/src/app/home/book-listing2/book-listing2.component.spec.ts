import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookListing2Component } from './book-listing2.component';

describe('BookListing2Component', () => {
  let component: BookListing2Component;
  let fixture: ComponentFixture<BookListing2Component>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BookListing2Component]
    });
    fixture = TestBed.createComponent(BookListing2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
