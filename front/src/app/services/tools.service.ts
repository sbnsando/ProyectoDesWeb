import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ToolsService {
  constructor(private http: HttpClient) { }


  getAllTools():Promise<Object>{
    return new Promise((resolve, reject) => {
      this.http.get('http://localhost:8081/tool/list').subscribe(data => {
        resolve(data);
      });
    });
  }
  getAllBrands():Promise<Object>{
    return new Promise((resolve, reject) => {
      this.http.get('http://localhost:8080/brand/list').subscribe(data => {
        resolve(data);
      });
    });
  }

}
