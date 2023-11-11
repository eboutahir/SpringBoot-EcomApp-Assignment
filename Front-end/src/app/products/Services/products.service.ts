import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http:HttpClient) { }

  getAllProducts(){
    return this.http.get("http://localhost:8080/api/products/AllProducts")
  }

  getAllCategories(){
    return this.http.get("http://localhost:8080/api/products/AllCategories")
  }

  getProductsByCategories(kyword:string){
    return this.http.get("http://localhost:8080/api/products/ProductByCategories/"+kyword)
  }

  getProductById(id:any){
    return this.http.get("http://localhost:8080/api/products/"+id)
  }
}
