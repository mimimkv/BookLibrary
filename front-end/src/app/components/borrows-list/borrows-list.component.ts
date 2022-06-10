import { Component, OnInit } from '@angular/core';
import { Borrow } from 'src/app/model/Borrow';
import { BorrowService } from 'src/app/services/borrow.service';

@Component({
  selector: 'app-borrows-list',
  templateUrl: './borrows-list.component.html',
  styleUrls: ['./borrows-list.component.css']
})
export class BorrowsListComponent implements OnInit {

  borrows: Borrow[] = [];

  constructor(private borrowService: BorrowService) { }

  ngOnInit(): void {
    this.borrowService.getAllBorrows()
      .subscribe((borrows) => this.borrows = borrows);
  }

}
