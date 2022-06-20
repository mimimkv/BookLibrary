import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Author } from 'src/app/model/Author';
import { Book } from 'src/app/model/Book';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {

  book: Book = new Book();
  //error: boolean = false;
  errorObj: Object = null;
  selectedTeam: string = "";

  constructor(private bookService: BookService, private router: Router) { }

  ngOnInit(): void {
    this.book.author = new Author();
  }

  goToBooksList() {
    this.router.navigate(["/books-list"]);
  }

  addBook() {
    this.bookService.addBook(this.book)
      .subscribe(() => {
        this.goToBooksList()
      }, (error: any) => {
        console.log(error)
        //this.error = true;
        if (error.status == 400) {
          this.errorObj = error.error;
        }
      });
  }

  onSubmit() {
      this.addBook();
  }

}
