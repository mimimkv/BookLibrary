import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddBookComponent } from './components/add-book/add-book.component';
import { BooksListComponent } from './components/books-list/books-list.component';
import { HomePageComponent } from './components/home-page/home-page.component';

const routes: Routes = [
  {path: "home", component: HomePageComponent},
  {path: "", redirectTo: "home", pathMatch: "full"},
  {path: "books-list", component: BooksListComponent},
  {path: "add-book", component: AddBookComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
