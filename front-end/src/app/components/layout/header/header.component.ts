import { Component, inject } from '@angular/core';
import { CategoryService } from '../../../services/category.service';
import { Category } from '../../../models/category';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  categoryService = inject(CategoryService);
  categories: Category[] = [];

  constructor() {
    this.getAll();
  }

  getAll() {
    this.categoryService.getAll().subscribe({
      next: (returnedCategories) => {
        this.categories = returnedCategories;
      },
      error: (error) => {
        console.log("Falha ao buscar a lista de categorias.");
      }
    })
  }
}
