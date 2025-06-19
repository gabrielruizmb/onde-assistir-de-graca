import { Component, inject } from '@angular/core';
import { HeaderComponent } from '../layout/header/header.component';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../models/category';
import { FilmService } from '../../services/film.service';
import { Film } from '../../models/film';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { ChannelService } from '../../services/channel.service';
import { Channel } from '../../models/channel';
import { Page } from '../../models/page';

@Component({
  selector: 'app-home',
  imports: [HeaderComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  channelService = inject(ChannelService);
  channels: Channel[] = [];

  categoryService = inject(CategoryService);
  categories: Category[] = [];

  filmService = inject(FilmService);
  films: Film[] = [];

  page: Page = new Page();
  pageNumber: number = 1;
  quantityPerPage: number = 10;

  currentCategoryId: string = '';

  userService = inject(UserService);
  currentUser: User = this.userService.getCurrentUser();

  constructor() {

    this.getAllCategories();
    this.getAllFilms();

  }
  
  getAllChannels() {
    this.channelService.getAll().subscribe({
      next: (returnedChannels) => {
        this.channels = returnedChannels;
        this.films = [];

      },
      error: (response) => {
        console.log("Erro ao obter os canais.");
      }
    })
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

  // getAllFilms() {
  //   this.filmService.getAll().subscribe({
  //     next: (returnedFilms) => {
  //       this.films = returnedFilms;
  //       this.channels = [];
  //     },
  //     error: (error) => {
  //       console.log("Falha ao buscar os filmes.");
  //     }
  //   })
  // }

  getAllFilms() {
    this.currentCategoryId = '';

    this.filmService.getAll(this.pageNumber, this.quantityPerPage).subscribe({
      next: (returnedPage) => {
        this.films = returnedPage.content;
        this.page = returnedPage;
        console.log(this.page);
        this.channels = [];
      },
      error: (error) => {
        console.log("Falha ao buscar os filmes.");
      }
    })
  }

  getAllFromCategory(id: string) {
    this.currentCategoryId = id;

    this.filmService.getAllFromCategory(
      id, this.pageNumber, this.quantityPerPage
    ).subscribe({
      next: (returnedPage) => {
        this.films = returnedPage.content;
        this.page = returnedPage;
        console.log(this.page);
        console.log(this.currentCategoryId);
        this.channels = [];
      },
      error: (error) => {
        console.log("Erro ao obter filmes por categoria.");
      }
    })
  }

  seeMore() {
    this.quantityPerPage += 10;

    this.currentCategoryId === '' ? 
      this.getAllFilms() : 
      this.getAllFromCategory(this.currentCategoryId)
  }

}
