import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

/**
 * Servicio que contiene los métodos para obtener las herramientas y las marcas
 */
@Injectable({
  providedIn: 'root'
})
export class ToolsService {
  constructor(private http: HttpClient) { }


  /**
   * 
   * @returns Promise con la lista de herramientas
   */
  getAllTools(): Promise<Object> {
    return new Promise((resolve, reject) => {
      this.http.get('http://localhost:8081/tool/list').subscribe(data => {
        resolve(data); 
      });
    });
  }

  /**
   * 
   * @returns Promise con la lista de marcas
   */
  getAllBrands(): Promise<Object> {
    return new Promise((resolve, reject) => {
      this.http.get('http://localhost:8080/brand/list').subscribe(data => {
        resolve(data);
      });
    });
  }

  /**
   * 
   * @param name Promise con la lista de herramientas filtradas por nombre

   */
  getFilteredToolsByName(name: string): Promise<Object> {
    return new Promise((resolve, reject) => {
      this.http.get('http://localhost:8081/tool/filter',
        {
          params: {
            name: name
          }
        }).subscribe(data => {
          resolve(data);
        });
    });
  }

  /**
   * 
   * @param name Promise con la lista de herramientas filtradas por marca
   */
  getFilteredToolsByBrand(name: string): Promise<Object> {
    return new Promise((resolve, reject) => {
      let brands: any = [];
      let idBrand: number = -1;

      this.getAllBrands()
        .then(data => {
          brands = data;
          idBrand = brands.find((b: any) => b.name.includes(name)).id || -1;
          
          if(idBrand > -1) {
            this.http.get('http://localhost:8081/tool/filterByBrand',
              {
                params: {
                  brandId: idBrand
                }
              }).subscribe(d => {
                resolve(d);
              });

          }
        });
    });
  }

  getToolById(idTool: string): Promise<Object> {
    return new Promise((resolve, reject) => {

      this.http.get('http://localhost:8081/tool/detail/'+idTool,
              ).subscribe(d => {
                resolve(d);
              });
    });
  }
}
