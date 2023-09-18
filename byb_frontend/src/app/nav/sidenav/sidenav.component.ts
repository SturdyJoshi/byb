import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent {
  genreShow : boolean = false;

  @Output () closeSideNav = new EventEmitter<boolean>();

  categoryClick(){
    this.genreShow = !this.genreShow;
  }

  close(){
    this.closeSideNav.emit(false)
  }
}
