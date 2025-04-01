import { Component, inject } from '@angular/core';
import { Category } from '../../../models/category';
import { CategoryService } from '../../../services/category.service';

@Component({
  selector: 'app-categories-list',
  imports: [],
  templateUrl: './categories-list.component.html',
  styleUrl: './categories-list.component.css'
})
export class CategoriesListComponent {
  categoryService = inject(CategoryService);
  categories: Category[] = [];

  constructor() {
    this.getAll();
  }

  getAll() {
    this.categoryService.getAll().subscribe({
      next: (returnedCategories) => {
        this.categories = returnedCategories;
        console.log("Sucesso!");
        console.log(this.categories);
        window.alert("Sucesso!");
      },
      error: (error) => {
        console.log("Falha ao buscar a lista de categorias.");
      }
    })
  }
}
