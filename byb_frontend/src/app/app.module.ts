import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {FormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import  SigninComponent  from './auth/signin/signin.component';
import { HomepageComponent } from './home/homepage/homepage.component';
import { HeaderpageComponent } from './header/headerpage/headerpage.component';
import { SidenavComponent } from './nav/sidenav/sidenav.component';
import { BookListingComponent } from './home/book-listing/book-listing.component';
import { SearchComponent } from './home/search/search.component';
import { BookListing2Component } from './home/book-listing2/book-listing2.component';
import { BookDetailsComponent } from './home/book-details/book-details.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SigninComponent,
    HomepageComponent,
    HeaderpageComponent,
    SidenavComponent,
    BookListingComponent,
    SearchComponent,
    BookListing2Component,
    BookDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
