import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Author } from 'src/app/model/Author';
import { Book } from 'src/app/model/Book';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-update-book',
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.css']
})
export class UpdateBookComponent implements OnInit {

  isbn: number;
  book: Book = new Book();

  constructor(private bookService: BookService, 
    private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.book.author = new Author();
    this.isbn = this.route.snapshot.params["isbn"];
    this.bookService.getBookByIsbn(this.isbn)
      .subscribe((data) => {this.book = data;},
        error => console.log(error));
  }

  onSubmit() {
    this.bookService.updateBook(this.isbn, this.book)
      .subscribe((data) => {
        console.log(data);
        this.goToBooksList();
      }, error => console.log(error));
  }

  goToBooksList() {
    this.router.navigate(["/books-list"]);
  }

}
