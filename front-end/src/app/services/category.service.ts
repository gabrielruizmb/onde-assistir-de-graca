import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../models/category';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  http = inject(HttpClient);
  baseUrl = 'http://localhost:8080/api/categories';

  userService = inject(UserService);

  constructor() { }
  
  getAll(): Observable<Category[]> {
    return this.http.get<Category[]>(this.baseUrl);
  }

  postCategory(category: Category): Observable<any> {
    return this.http.post<any>(this.baseUrl, category,
      {headers: {'Authorization': 'Bearer ' + this.userService.getToken()}});
  }
}
