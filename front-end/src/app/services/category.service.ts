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

  getById(id: string): Observable<Category> {
    return this.http.get<Category>(this.baseUrl + '/' + id,
      {headers: {'Authorization': 'Bearer ' + this.userService.getToken()}});
  }

  postCategory(category: Category): Observable<any> {
    return this.http.post<any>(this.baseUrl, category,
      {headers: {'Authorization': 'Bearer ' + this.userService.getToken()}});
  }

  putCategory(category: Category): Observable<any> {
    return this.http.put<any>(this.baseUrl + '/' + category.id, category,
      {headers: {'Authorization': 'Bearer ' + this.userService.getToken()}});
  }

  deleteCategory(id: string): Observable<any> {
    return this.http.delete<any>(this.baseUrl + '/' + id,
      {headers: {'Authorization': 'Bearer ' + this.userService.getToken()}});
  }
}
