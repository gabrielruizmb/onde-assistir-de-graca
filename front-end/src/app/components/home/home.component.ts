import { Component, inject } from '@angular/core';
import { HeaderComponent } from '../layout/header/header.component';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../models/category';
import { FilmService } from '../../services/film.service';
import { Film } from '../../models/film';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';

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
  currentUser: User = this.userService.getCurrentUser();

  constructor() {

    if (this.currentUser.roles.includes("ROLE_ADMIN")) {
      console.log("Admin");
    }

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

  getAllFromCategory(id: string) {
    this.filmService.getAllFromCategory(id).subscribe({
      next: (returnedFilms) => {
        this.films = returnedFilms;
      },
      error: (error) => {
        console.log("Erro ao obter filmes por categoria.");
      }
    })
  }

}
