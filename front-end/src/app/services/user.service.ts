import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { GenericResponse } from '../models/generic-response';
import { Observable } from 'rxjs';
import { Login } from '../models/login';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  http = inject(HttpClient);
  url = "http://localhost:8080/api/users/sign-in";

  constructor() { }

  signIn(login: Login): Observable<GenericResponse> {
    return this.http.post<GenericResponse>(this.url, login);
  }

  setToken(token: string) {
    localStorage.setItem("token", token);
  }

  removeToken() {
    localStorage.removeItem("token");
  }
}
