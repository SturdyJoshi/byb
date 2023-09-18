import { Component } from '@angular/core';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.scss']
})
export class BookDetailsComponent {
  list = Array.from({ length: 10 }, (_, index) => index + 1);
  sidenavmenu : boolean = false;

  openSideNav(bool : boolean){
    this.sidenavmenu = bool;
  }

  sidenavClose(){
    this.sidenavmenu = false;
  }
}
