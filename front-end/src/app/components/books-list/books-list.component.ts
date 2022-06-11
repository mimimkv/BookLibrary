import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { Book } from 'src/app/model/Book';
import { User } from 'src/app/model/User';
import { BookService } from 'src/app/services/book.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.css']
})
export class BooksListComponent implements OnInit {

  title: string = "Book Library";
  books: Book[] = [];
  isBookSelected = false;
  userEmail: string = "";
  selectedBookId: number = -1;
  errorMessage: string;
  selectedUserId: number;

  constructor(private bookService: BookService, private userService: UserService,
    private router: Router) { }

  ngOnInit(): void {
    this.bookService.getAllBooks()
      .subscribe((books) => this.books = books);
      console.log(this.books);
  }

  borrow(bookIsbn: number) {
      this.isBookSelected = true;
      this.selectedBookId = bookIsbn;
  }

  borrowBook(bookIsbn: number, userEmail: string) {
      this.userService.getUserByEmail(userEmail)
      .subscribe((user: User) => {
        this.selectedUserId = user.id;
      });

      //fix: We have to wait for the upper operation to use the value of 
      //selectedUserId below()
      this.userService.borrowBook(bookIsbn, this.selectedUserId)
        .subscribe((user) => console.log(this.selectedUserId));
  }

  submitEmail() {
    this.borrowBook(this.selectedBookId, this.userEmail);
    this.closeEmailForm();
  }

  closeEmailForm() {
    this.isBookSelected = false;
    this.selectedBookId = -1;
  }

  updateBook(isbn: number) {
    this.router.navigate(["update-book", isbn]);
  }

}
