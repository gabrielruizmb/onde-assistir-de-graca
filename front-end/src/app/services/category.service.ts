import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../models/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  http = inject(HttpClient);
  apiUrl = 'http://localhost:8080/api/categories';

  constructor() { }
  
  getAll(): Observable<Category[]> {
    return this.http.get<Category[]>(this.apiUrl);
  }

}
