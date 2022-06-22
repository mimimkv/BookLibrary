import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddBookComponent } from './components/add-book/add-book.component';
import { AddUserComponent } from './components/add-user/add-user.component';
import { BooksListComponent } from './components/books-list/books-list.component';
import { BorrowsListComponent } from './components/borrows-list/borrows-list.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { UpdateBookComponent } from './components/update-book/update-book.component';
import { UpdateUserComponent } from './components/update-user/update-user.component';
import { UsersListComponent } from './components/users-list/users-list.component';

const routes: Routes = [
  { path: 'home', component: HomePageComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'books-list', component: BooksListComponent },
  { path: 'add-book', component: AddBookComponent },
  { path: 'users-list', component: UsersListComponent },
  { path: 'add-user', component: AddUserComponent },
  { path: 'borrows-list', component: BorrowsListComponent },
  { path: 'update-book/:isbn', component: UpdateBookComponent },
  { path: 'update-user/:id', component: UpdateUserComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
