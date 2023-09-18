import { Component } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent {
  list = Array.from({ length: 10 }, (_, index) => index + 1);
  sidenavmenu : boolean = false;

  openSideNav(bool : boolean){
    this.sidenavmenu = bool;
  }

  sidenavClose(){
    this.sidenavmenu = false;
  }

}
