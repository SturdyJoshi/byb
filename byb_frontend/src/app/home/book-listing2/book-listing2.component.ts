import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book-listing2',
  templateUrl: './book-listing2.component.html',
  styleUrls: ['./book-listing2.component.scss']
})
export class BookListing2Component {

  constructor(private router: Router){}

  getDetails(){
    this.router.navigateByUrl('bookdetails');
  }
}
