import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss']
})
export class HomepageComponent {
  list = Array.from({ length: 10 }, (_, index) => index + 1);
  sidenavmenu : boolean = false;

  openSideNav(bool : boolean){
    this.sidenavmenu = bool;
  }

  sidenavClose(){
    this.sidenavmenu = false;
  }

}
