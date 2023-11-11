import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

interface Response {
  message: string;
}

@Injectable({
  providedIn: 'root'
})
export class CartsService {

  constructor(private http:HttpClient) { }

  addToCart(Model:any){
    return this.http.post<Response>("http://localhost:8080/api/carts/Add",Model,{observe : 'response'})
  }
}
