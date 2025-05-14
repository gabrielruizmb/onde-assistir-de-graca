import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Login } from '../models/login';
import { Observable } from 'rxjs';
import { Genericresponse } from '../models/genericresponse';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  http = inject(HttpClient);
  url = "http://localhost:8080/api/users/sign-in";

  constructor() { }

  signIn(login: Login): Observable<Genericresponse> {
    return this.http.post<Genericresponse>(this.url, login);
  }
}
