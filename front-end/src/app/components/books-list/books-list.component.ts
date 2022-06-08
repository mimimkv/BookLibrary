import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/model/Book';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.css']
})
export class BooksListComponent implements OnInit {

  title: string = "Book Library";
  books: Book[] = [];

  constructor(private bookService: BookService) { }

  ngOnInit(): void {
    /*this.books = [
      {
        isbn: 1,
        title: "Pod igoto",
        author: {
          firstName: "Ivan",
          lastName: "Vazov"
        },
        category: "history"
      }
    ]*/

    this.bookService.getAllBooks()
      .subscribe((books) => this.books = books);
      console.log(this.books);
  }

}
