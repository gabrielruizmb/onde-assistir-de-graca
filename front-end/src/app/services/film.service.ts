import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Film } from '../models/film';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FilmService {

  http = inject(HttpClient);
  baseUrl = "http://localhost:8080/api/films";

  constructor() { }

  getAll(): Observable<Film[]> {
    return this.http.get<Film[]>(this.baseUrl);
  }

  getFilm(id: string): Observable<Film> {
    return this.http.get<Film>(this.baseUrl + '/' + id);
  }
}
