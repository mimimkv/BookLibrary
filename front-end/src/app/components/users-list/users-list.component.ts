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
    this.getAllUsers();
  }

  getAllUsers() {
    this.userService.getAllUsers()
      .subscribe((users) => this.users = users);
  }

  updateUser(id: number) {
    this.router.navigate(["/update-user", id]);
  }

  deleteUser(id: number) {
    this.userService.deleteUser(id)
      .subscribe(() => {
        console.log("User " + id + " was deleted")
        this.getAllUsers();
      });
  }

  returnBorrow(userId: number) {
    this.isUserSelected = true;
    this.selectedUserId = userId;
  }

  async removeBorrow(userId: number, bookIsbn: number) {
    this.selectedBorrowId = await this.borrowService.getBorrow(userId, bookIsbn);

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
