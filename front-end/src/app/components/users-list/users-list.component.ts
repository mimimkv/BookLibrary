import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/User';
import { BorrowService } from 'src/app/services/borrow.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {

  users: User[] = [];

  bookIsbn: number;
  selectedBorrowId: number = -1;
  selectedUserId: number = -1;
  isUserSelected: boolean = false;

  constructor(private userService: UserService, private borrowService: BorrowService,
    private router: Router) { }

  ngOnInit(): void {
    this.userService.getAllUsers()
      .subscribe((users) => this.users = users);
  }

  updateUser(id: number) {
    this.router.navigate(["/update-user", id]);
  }

  returnBorrow(userId: number) {
    this.isUserSelected = true;
    this.selectedUserId = userId;
  }

  async removeBorrow(userId: number, bookIsbn: number) {
    //let user = this.userService.getUserById(userId);
    this.selectedBorrowId = await this.borrowService.getBorrow(userId, bookIsbn);

    console.log("here");
    console.log(this.selectedBorrowId);

    this.borrowService.deleteBorrow(this.selectedBorrowId)
      .subscribe(() => console.log(this.selectedBorrowId))
  }

  submitBookIsbn() {
    this.removeBorrow(this.selectedUserId, this.bookIsbn);
    this.closeBookIsbnForm();
  }

  closeBookIsbnForm() {
    this.isUserSelected = false;
    this.selectedUserId = -1;
  }
}
