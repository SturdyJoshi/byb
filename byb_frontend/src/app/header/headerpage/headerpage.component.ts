import { Component, EventEmitter, Output } from '@angular/core';
import { Route, Router, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-headerpage',
  templateUrl: './headerpage.component.html',
  styleUrls: ['./headerpage.component.scss']
})
export class HeaderpageComponent {
  searchValue: string = '';
  @Output() sidenavEvent = new EventEmitter<boolean>();

  constructor( private router : Router){}

  searchClear(){
    this.searchValue = '';
  }

  searchBook(){
    console.log(this.searchValue);
    this.router.navigateByUrl("searched")
  }

  isMenuOpen: boolean = false;

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }

  sideNavToggle(){
    this.sidenavEvent.emit(true);
  }
}
