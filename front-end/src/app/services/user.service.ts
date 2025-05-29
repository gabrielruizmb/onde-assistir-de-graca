import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { GenericResponse } from '../models/generic-response';
import { Observable } from 'rxjs';
import { Login } from '../models/login';
import { jwtDecode, JwtPayload } from 'jwt-decode';
import { User } from '../models/user';
import { UserRegister } from '../models/user-register';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  http = inject(HttpClient);
  url = "http://localhost:8080/api/users";

  constructor() { }

  signIn(login: Login): Observable<GenericResponse> {
    return this.http.post<GenericResponse>(this.url + "/sign-in", login);
  }

  signUp(userRegister: UserRegister): Observable<GenericResponse> {

    return this.http.post<GenericResponse>(this.url + "/sign-up", userRegister, 
      {headers: {'Authorization': 'Bearer ' + this.getToken()}});
  }

  setToken(token: string) {
    localStorage.setItem("token", token);
  }

  removeToken() {
    localStorage.removeItem("token");
  }

  getToken() {
    return localStorage.getItem("token");
  }

  jwtDecode() {
    let token = this.getToken();

    if (token) {
      return jwtDecode<JwtPayload>(token);
    } else return "";
  }

  hasRole(role: string) {
    let user = this.jwtDecode() as User;

    if (user.roles.includes(role)) {
      return true;
    } else return false;
  }

  getCurrentUser() {
    return this.jwtDecode() as User;
  }

  getTokenPayload() {
    return this.jwtDecode() as JwtPayload;
  }
}
