import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient,
    private cookies: CookieService) { }

  login(email: string, password: string): Observable<any> {
    const headers = new HttpHeaders();
    const body = JSON.stringify({})
    return this.http.post("http://localhost:8082/login", {
      params:
        {
          "email": email,
          "password": password
      }
    } );
  }

  setToken(token: string){
    this.cookies.set("token", token);
  }

  getToken(){
    return this.cookies.get("token");
  }
}
