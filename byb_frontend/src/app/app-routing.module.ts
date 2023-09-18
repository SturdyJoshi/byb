import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import SigninComponent from './auth/signin/signin.component';
import { HomepageComponent } from './home/homepage/homepage.component';
import { SearchComponent } from './home/search/search.component';
import { BookDetailsComponent } from './home/book-details/book-details.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'signin',
    component: SigninComponent
  },
  {
    path: 'home',
    component : HomepageComponent
  },
  {
    path: 'searched',
    component: SearchComponent
  },
  {
    path : 'bookdetails',
    component : BookDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
