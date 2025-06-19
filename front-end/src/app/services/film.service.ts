import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Film } from '../models/film';
import { Observable } from 'rxjs';
import { GenericResponse } from '../models/generic-response';
import { UserService } from './user.service';
import { environment } from '../../environments/environment';
import { Page } from '../models/page';

@Injectable({
  providedIn: 'root'
})
export class FilmService {

  http = inject(HttpClient);
  baseUrl = environment.SERVER + "/api/films";

  userService = inject(UserService);

  constructor() { }

  // getAll(): Observable<Film[]> {
  //   return this.http.get<Film[]>(this.baseUrl);
  // }

  getAll(pageNumber: number, quantityPerPage: number): Observable<Page> {
    return this.http.get<Page>(this.baseUrl + '/' + pageNumber + '/' + quantityPerPage);
  }

  // getAllFromCategory(id: string): Observable<Film[]> {
  //   return this.http.get<Film[]>(this.baseUrl + '/by-category/' + id);
  // }

  getAllFromCategory(id: string, pageNumber: number, quantityPerPage: number): Observable<Page> {
    return this.http.get<Page>(this.baseUrl + '/by-category/' + id  + '/' + pageNumber + '/' + quantityPerPage);
  }

  getFilm(id: string): Observable<Film> {
    return this.http.get<Film>(this.baseUrl + '/' + id);
  }

  postFilm(film: Film): Observable<any> {
    return this.http.post<any>(this.baseUrl, film, 
      {headers: {'Authorization': 'Bearer ' + this.userService.getToken()}});
  }

  putFilm(film: Film, id: string): Observable<any> {
    return this.http.put<any>(this.baseUrl + '/' +id, film, 
      {headers: {'Authorization': 'Bearer ' + this.userService.getToken()}});
  }

  deleteFilm(id: string): Observable<any> {
    return this.http.delete<any>(this.baseUrl + '/' +id, 
      {headers: {'Authorization': 'Bearer ' + this.userService.getToken()}});
  }
}
