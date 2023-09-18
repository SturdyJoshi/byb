import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book-listing',
  templateUrl: './book-listing.component.html',
  styleUrls: ['./book-listing.component.scss']
})
export class BookListingComponent {
  constructor(private router: Router){}

  getDetails(){
    this.router.navigateByUrl('bookdetails');
  }
}
