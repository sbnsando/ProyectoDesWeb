import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private http: HttpClient) { }

  getAllUsers(): Promise<Object> {
    return new Promise((resolve, reject) => {
      this.http.get('http://localhost:8082/users/list').subscribe(data => {
        resolve(data); 
      });
    });
  }

  login(email: string, password: string): Promise<any> {
    const userLogin = { email: email, password: password };
    return new Promise((resolve, reject) => {
      this.http.post('http://localhost:8082/users/login', userLogin)
        .subscribe(data => {
          resolve(data);
        }, error => {
          if (error.error && error.error.error) {
            reject(error.error.error);
          } else {
            reject('Error en la respuesta del servidor');
          }
        });
    });
  }
  
}
