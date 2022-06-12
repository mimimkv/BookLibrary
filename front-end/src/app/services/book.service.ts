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

  getBookByIsbn(isbn: number): Observable<Book> {
    const url = this.apiUrl + "/" + isbn;
    return this.httpClient.get<Book>(url);
  }

  updateBook(booksIsbn: number, book: Book) {
    const url = this.apiUrl + "/" + booksIsbn;
    return this.httpClient.put<Book>(url, book);
  }

  addBook(book: Book): Observable<Book> {
    return this.httpClient.post<Book>(this.apiUrl, book);
  }
}
