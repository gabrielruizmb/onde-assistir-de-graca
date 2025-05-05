import { Component, inject } from '@angular/core';
import { HeaderComponent } from '../layout/header/header.component';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../models/category';

@Component({
  selector: 'app-home',
  imports: [HeaderComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  categoryService = inject(CategoryService);
  categories: Category[] = [];

  constructor() {
    this.getAllCategories();
  }

  getAllCategories() {
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
