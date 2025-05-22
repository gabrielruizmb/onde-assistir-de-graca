import { Component, inject } from '@angular/core';
import { Film } from '../../../models/film';
import { HeaderComponent } from '../../layout/header/header.component';
import { CategoryService } from '../../../services/category.service';
import { Category } from '../../../models/category';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-film-form',
  imports: [HeaderComponent, FormsModule],
  templateUrl: './film-form.component.html',
  styleUrl: './film-form.component.css'
})
export class FilmFormComponent {

  film: Film = new Film();
  categoryService = inject(CategoryService);
  categoriesList: Category[] = [];

  constructor() {
    this.getAllCategories();
  }

  getAllCategories() {
    this.categoryService.getAll().subscribe({
      next: (returnedCategories) => {
        this.categoriesList = returnedCategories;
      },
      error: (error) => {
        console.log("Falha ao buscar a lista de categorias.");
      }
    })
  }
}
