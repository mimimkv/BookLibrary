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

  getUserByEmail(userEmail: string): Observable<User> {
    const url = this.apiUrl + "/email?email=" + userEmail;
    return this.httpClient.get<User>(url);
  }

  borrowBook(bookIsbn: number, userId: number): Observable<User> {
    const url = this.apiUrl + "/" + userId + "/borrow/" + bookIsbn;
    console.log(url);
    return this.httpClient.post<User>(url, null);
  }
}
