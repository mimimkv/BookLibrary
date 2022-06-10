import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Borrow } from '../model/Borrow';

@Injectable({
  providedIn: 'root'
})
export class BorrowService {

  private apiUrl = "http://localhost:8080/library/borrows";

  constructor(private httpClient: HttpClient) { }

  getAllBorrows(): Observable<Borrow[]> {
    return this.httpClient.get<Borrow[]>(this.apiUrl);
  }
}
