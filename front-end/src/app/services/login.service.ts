import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Login } from '../models/login';
import { Observable } from 'rxjs';
import { GenericResponse } from '../models/generic-response';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  http = inject(HttpClient);
  url = "http://localhost:8080/api/users/sign-in";

  constructor() { }

  signIn(login: Login): Observable<GenericResponse> {
    return this.http.post<GenericResponse>(this.url, login);
  }
}
