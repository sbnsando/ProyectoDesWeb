import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ToolsService {
  constructor(private http: HttpClient) { }

  getAllTools(){
    // this.http.get('').subscribe(data ->{
    //   console.log(data);
    // });
  }
}
