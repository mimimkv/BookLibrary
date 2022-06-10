import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../model/Book';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private apiUrl = "http://localhost:8080/library/books";

  constructor(private httpClient: HttpClient) { }

  getAllBooks(): Observable<Book[]> {
    return this.httpClient.get<Book[]>(this.apiUrl);
  }
}
