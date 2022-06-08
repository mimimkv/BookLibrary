import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/model/Book';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {

  book: Book = new Book();

  constructor() { }

  ngOnInit(): void {
  }

  onSubmit() {
    console.log("submit");
  }

}
