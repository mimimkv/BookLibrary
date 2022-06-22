import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Borrow } from '../model/Borrow';

@Injectable({
  providedIn: 'root',
})
export class BorrowService {
  private apiUrl = 'http://localhost:8080/library/borrows';

  constructor(private httpClient: HttpClient) {}

  getAllBorrows(page: number): Observable<any> {
    const url = this.apiUrl + '?page=' + page;
    return this.httpClient.get<any>(url);
  }

  async getBorrow(userId: number, bookIsbn: number): Promise<number> {
    const url = this.apiUrl + '/userId/' + userId + '/bookIsbn/' + bookIsbn;
    
    return this.httpClient
      .get<Borrow>(url)
      .toPromise()
      .then((borrow: Borrow) => {
        return borrow.id;
      })
      .catch((error) => {
        console.log(error);
        return -1;
      });
  }

  deleteBorrow(borrowId: number): Observable<any> {
    const url = this.apiUrl + '/' + borrowId;
    return this.httpClient.delete<Borrow>(this.apiUrl + '/' + borrowId);
  }
}
