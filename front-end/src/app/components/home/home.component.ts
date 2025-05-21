import { Component, inject } from '@angular/core';
import { HeaderComponent } from '../layout/header/header.component';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../models/category';
import { FilmService } from '../../services/film.service';
import { Film } from '../../models/film';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-home',
  imports: [HeaderComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  categoryService = inject(CategoryService);
  categories: Category[] = [];

  filmService = inject(FilmService);
  films: Film[] = [];

  userService = inject(UserService);

  constructor() {
    this.getAllCategories();
    this.getAllFilms();
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

  getAllFilms() {
    this.filmService.getAll().subscribe({
      next: (returnedFilms) => {
        this.films = returnedFilms;
      },
      error: (error) => {
        console.log("Falha ao buscar os filmes.");
      }
    })
  }

  goToFilmDetails() {
    
  }
}
