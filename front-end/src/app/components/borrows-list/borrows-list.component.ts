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
  totalElements: number;
  size: number;
  page: number = 0;
  filterString: any;


  constructor(private borrowService: BorrowService) { }

  ngOnInit(): void {
    this.getAllBorrows();
  }


  getAllBorrows() {
    this.borrowService.getAllBorrows(this.page)
      .subscribe((response: any) => {
        this.borrows = response.content;
        this.size = response.size;
        this.totalElements = response.totalElements;
      });
  }

  pageChangeEvent(event: number) {
    this.page = event;
    this.getAllBorrows();
  }


}
