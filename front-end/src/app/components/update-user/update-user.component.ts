import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  id: number;
  user: User = new User();
  errorObj: Object = null;

  constructor(private userService: UserService,
    private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params["id"];
    this.userService.getUserById(this.id)
      .subscribe((data) => {this.user = data;},
        error => console.log(error));
  }

  onSubmit() {
    this.userService.updateUser(this.id, this.user)
      .subscribe((data) => {
        console.log(data);
        this.goToUsersList();
      }, (error: any) => {
        console.log(error)
        if (error.status == 400) {
          this.errorObj = error.error;
        }
      });
  }

  goToUsersList() {
    this.router.navigate(["/users-list"]);
  }

}
