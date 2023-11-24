import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8080/api/alianza'; 

  constructor(private http: HttpClient) { }

  createNewUser(userData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}`, userData);
  }

  searchUserBySharedKey(sharedKey: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/${sharedKey}`);
  }

  getAllUsers(): Observable<any> {
    return this.http.get(`${this.apiUrl}`);
  }
}
