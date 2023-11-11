import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HeadersComponent } from 'src/app/shared/Componenents/headers/headers.component';

interface Response {
  message: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient ) { }

  checkLogin(user: any){
    return this.http.post<Response>('http://localhost:8080/api/users/login' ,user ,  { observe: 'response' } )
  }

  checkRegister(user: any){
    return this.http.post<Response>('http://localhost:8080/api/users/register' ,user ,  { observe: 'response' } )
  }



}
