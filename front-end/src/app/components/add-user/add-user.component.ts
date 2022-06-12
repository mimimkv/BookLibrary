import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  user: User = new User();

  constructor(private userSerice: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  goToUsersList() {
    this.router.navigate(["/users-list"]);
  }

  addUser() {
    this.userSerice.addUser(this.user)
      .subscribe(() => this.goToUsersList());
  }

  onSubmit() {
    this.addUser();
  }
}
