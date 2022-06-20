import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { BooksListComponent } from './components/books-list/books-list.component';
import { AddBookComponent } from './components/add-book/add-book.component';
import { FormsModule } from '@angular/forms';
import { UsersListComponent } from './components/users-list/users-list.component';
import { AddUserComponent } from './components/add-user/add-user.component';
import { BorrowsListComponent } from './components/borrows-list/borrows-list.component';
import { UpdateBookComponent } from './components/update-book/update-book.component';
import { UpdateUserComponent } from './components/update-user/update-user.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { Ng2SearchPipeModule } from 'ng2-search-filter';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    BooksListComponent,
    AddBookComponent,
    UsersListComponent,
    AddUserComponent,
    BorrowsListComponent,
    UpdateBookComponent,
    UpdateUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgxPaginationModule,
    Ng2SearchPipeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
