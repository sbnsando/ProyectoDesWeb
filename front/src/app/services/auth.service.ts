import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  getAllUsers(): Promise<Object> {
    return new Promise((resolve, reject) => {
      this.http.get('http://localhost:8082/users/list').subscribe(data => {
        resolve(data);
      });
    });
  }

  deleteUser(id: number): Promise<Object> {
    return new Promise((resolve, reject) => {
      this.http.delete(`http://localhost:8082/users/delete/${id}`).subscribe(data => {
        resolve(data);
      });
    });
  }
}
