import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = "http://localhost:8080/library/users";

  constructor(private httpClient: HttpClient) { }

  getAllUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.apiUrl);
  }

  async getUserByEmail(userEmail: string): Promise<number> {
    let id = null;
    console.log('>>>>>>>>>>>>>>>>>>> g');
    const url = this.apiUrl + "/email?email=" + userEmail;
    return this.httpClient.get<User>(url).toPromise()
      .then((user: User) => {
        console.log(user);
        id = user.id;
        return id;
      })
      .catch((error) => {
        console.log(error)
        return 0;
      });
    // let promise = new Promise<User>((resolve, reject) => {
    //   this.httpClient.get<User>(url).toPromise()
    //   .then((user: User) => {
        
    //   console.log('>>>>>>>>>>>>>>>>>>> ', user.id);
    //     selectedUserId = user.id});
    // })

    // console.log('>>>>>>>>>>>>>>>>>>> g', id);
    //return id;    
  }

  getUserById(id: number): Observable<User> {
    const url = this.apiUrl + "/" + id;
    return this.httpClient.get<User>(url);
  }

  borrowBook(bookIsbn: number, userId: number): Observable<User> {
    const url = this.apiUrl + "/" + userId + "/borrow/" + bookIsbn;
    console.log(url);
    return this.httpClient.post<User>(url, null);
  }

  updateUser(id: number, user: User): Observable<User> {
    const url = this.apiUrl + "/" + id;
    return this.httpClient.put<User>(url, user);
  }

  addUser(user: User): Observable<User> {
    return this.httpClient.post<User>(this.apiUrl, user);
  }

}